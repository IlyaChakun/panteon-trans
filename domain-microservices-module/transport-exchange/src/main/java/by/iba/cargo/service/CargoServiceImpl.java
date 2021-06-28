package by.iba.cargo.service;

import by.iba.cargo.domain.Cargo;
import by.iba.cargo.domain.CargoType;
import by.iba.cargo.dto.CargoDTO;
import by.iba.cargo.dto.CargoReqDTO;
import by.iba.cargo.dto.CargoSearchCriteriaDTO;
import by.iba.cargo.dto.mapper.CargoDimensionsMapperDTO;
import by.iba.cargo.dto.mapper.CargoMapperDTO;
import by.iba.cargo.mail.CargoMailServiceImpl;
import by.iba.cargo.repository.CargoRepository;
import by.iba.cargo.repository.CargoTypeRepository;
import by.iba.cargo.specifications.CargoSpecifications;
import by.iba.common.domain.CargoStowageMethod;
import by.iba.common.domain.TruckBodyType;
import by.iba.common.dto.PageWrapper;
import by.iba.common.dto.mapper.LoadingLocationMapperDTO;
import by.iba.common.dto.mapper.PaymentMapperDTO;
import by.iba.common.dto.mapper.UnLoadingLocationMapperDTO;
import by.iba.common.exception.ResourceNotFoundException;
import by.iba.common.repository.CargoStowageMethodRepository;
import by.iba.common.repository.TruckBodyTypeRepository;
import by.iba.domain.ConfirmationToken;
import by.iba.domain.User;
import by.iba.repository.ConfirmationTokenRepository;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;

@Service
@AllArgsConstructor
@Slf4j
public class CargoServiceImpl implements CargoService {

    private final CargoDimensionsMapperDTO cargoDimensionsMapperDTO;
    private final CargoMailServiceImpl cargoMailService;
    private final LoadingLocationMapperDTO loadingLocationMapperDTO;
    private final UnLoadingLocationMapperDTO unLoadingLocationMapperDTO;
    private final CargoRepository cargoRepository;
    private final CargoMapperDTO cargoMapperDTO;
    private final CargoStowageMethodRepository cargoStowageMethodRepository;
    private final TruckBodyTypeRepository truckBodyTypeRepository;
    private final CargoTypeRepository cargoTypeRepository;
    private final PaymentMapperDTO paymentMapperDTO;
    private final ConfirmationTokenRepository confirmationTokenRepository;


    @Transactional
    @CachePut(value = "id", key = "#p0")
    @Override
    public CargoDTO save(CargoReqDTO cargoReqDTO, User user) {

        log.info("Start saving the cargo");

        String token = String.valueOf(confirmationTokenRepository.findByUserId(user.getUserId()));
        String email = user.getEmail();
        Cargo cargo = new Cargo();
        Cargo savedCargo = cargoRepository.save(updateCargo(cargoReqDTO, cargo));
        cargoMailService.sendEmailAboutCargoSave(email, token);
        log.info("Finish saving cargo with id =" + savedCargo.getId());

        return cargoMapperDTO.toDto(savedCargo);
    }

    @Transactional
    @CachePut(value = "id", key = "#p0")
    @Override
    public CargoDTO update(Long cargoId, CargoReqDTO cargoReqDTO) {
        log.info("Start update cargo with id = {} ", cargoId);

        Cargo cargo = cargoRepository
                .findById(cargoId)
                .orElseThrow(() -> new ResourceNotFoundException("cargo with id = " + cargoId + " not found"));

        Cargo savedCargo = cargoRepository.save(updateCargo(cargoReqDTO, cargo));

        log.info("Finish update cargo with id =" + savedCargo.getId());

        return cargoMapperDTO.toDto(savedCargo);
    }

    @Transactional
    @CacheEvict(value = "id", key = "#cargoId")
    @Override
    public void delete(Long cargoId) {
        log.info("Start deleting cargo with id = {} ", cargoId);

        Cargo cargo = cargoRepository
                .findById(cargoId)
                .orElseThrow(() -> new ResourceNotFoundException("cargo with id = " + cargoId + " not found "));

        cargo.setDeletionDate(LocalDate.now());
        cargoRepository.save(cargo);

        log.info("Cargo with id = {} has been deleted ", cargoId);
    }

    @Override
    public CargoDTO findById(Long cargoId) {

        log.info("Start findById cargo with id = {} ", cargoId);

        Cargo cargo = cargoRepository.findById(cargoId)
                .orElseThrow(() -> new ResourceNotFoundException("exception.cargo.not_found_by_id" + cargoId));

        log.info("Cargo with id = {} has been find ", cargoId);

        return cargoMapperDTO.toDto(cargo);
    }

    @Transactional
    @Override
    public PageWrapper<CargoDTO> findAll(Integer page, Integer size, CargoSearchCriteriaDTO cargoSearchCriteriaDTO) {

        log.info("There was a request to findAll cargo with page " + page + "and size" + size);

        Specification<Cargo> specification =
                Specification.where(CargoSpecifications
                        .notDeleted())
                        .and(CargoSpecifications.getAllCargoByCountryId(cargoSearchCriteriaDTO.getCountryId()));

        Pageable pageable =
                PageRequest.of(page, size);

        Page<Cargo> cargoPage =
                cargoRepository.findAll(specification, pageable);

        log.info("Method response posted to findAll cargo with page " + page + "and size " + size);

        return
                new PageWrapper<>(cargoMapperDTO
                        .toDtoList(cargoPage.toList()),
                        cargoPage.getTotalPages(),
                        cargoPage.getTotalElements());
    }

    @Transactional
    public Cargo updateCargo(CargoReqDTO cargoReqDTO, Cargo cargo) {

        cargo.setLoadingDate(cargoReqDTO.getLoadingDate());
        cargo.setUnloadingDate(cargoReqDTO.getUnloadingDate());
        cargo.setTemperatureMode(cargoReqDTO.getTemperatureMode());
        cargo.setDescription(cargoReqDTO.getDescription());
        cargo.setPayment(paymentMapperDTO.toEntity(cargoReqDTO.getPayment()));
        cargo.setCargoDimensions(cargoDimensionsMapperDTO.toEntity(cargoReqDTO.getCargoDimensions()));
        cargo.setLoadingLocation(loadingLocationMapperDTO.toEntity(cargoReqDTO.getLoadingLocation()));
        cargo.setUnloadingLocation(unLoadingLocationMapperDTO.toEntity(cargoReqDTO.getUnloadingLocation()));

        for (Long id : cargoReqDTO.getCargoStowageMethodIds()) {
            TruckBodyType truckBodyType = truckBodyTypeRepository.findById(id)
                    .orElseThrow(() -> new ResourceNotFoundException("TruckBodyType with id =" + id + " not found"));
            cargo.getTruckBodyTypes().add(truckBodyType);
        }

        for (Long id : cargoReqDTO.getCargoStowageMethodIds()) {
            CargoStowageMethod cargoStowageMethod = cargoStowageMethodRepository.findById(id)
                    .orElseThrow(() -> new ResourceNotFoundException("CargoStowageMethod with id =" + id + " not found"));
            cargo.getCargoStowageMethods().add(cargoStowageMethod);
        }

        CargoType cargoType = cargoTypeRepository.findById(cargoReqDTO.getCargoTypeId())
                .orElseThrow(() -> new ResourceNotFoundException("CargoType with id =" + cargoReqDTO.getCargoTypeId() + " not found"));
        cargo.setCargoType(cargoType);

        return cargo;
    }
}