package by.iba.cargo.service;

import by.iba.cargo.domain.CargoOffer;
import by.iba.cargo.domain.CargoType;
import by.iba.cargo.dto.CargoOfferDTO;
import by.iba.cargo.dto.CargoOfferReqDTO;
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
import by.iba.repository.UserRepository;
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
    private final UserRepository userRepository;


    @Transactional
    @CachePut(value = "id", key = "#p0")
    @Override
    public CargoOfferDTO save(CargoOfferReqDTO cargoOfferReqDTO) {

        log.info("Start saving the cargo");
        String email = userRepository.findByUserId(cargoOfferReqDTO.getId()).getEmail();
        CargoOffer cargoOffer = mapToCargo(cargoOfferReqDTO);
        CargoOffer savedCargoOffer = cargoRepository.save(cargoOffer);
        cargoMailService.sendSaveCargoNotification(email);
        log.info("Finish saving cargo with id =" + savedCargoOffer.getId());
        return cargoMapperDTO.toDto(savedCargoOffer);
    }


    @Transactional
    @CacheEvict(value = "id", key = "#cargoId")
    @Override
    public void delete(Long cargoId) {
        log.info("Start deleting cargo with id = {} ", cargoId);

        CargoOffer cargoOffer = cargoRepository
                .findById(cargoId)
                .orElseThrow(() -> new ResourceNotFoundException("cargo with id = " + cargoId + " not found "));

        cargoOffer.setDeletionDate(LocalDate.now());
        cargoRepository.save(cargoOffer);

        log.info("Cargo with id = {} has been deleted ", cargoId);
    }

    @Override
    public CargoOfferDTO findById(Long cargoId) {

        log.info("Start findById cargo with id = {} ", cargoId);

        CargoOffer cargoOffer = cargoRepository.findById(cargoId)
                .orElseThrow(() -> new ResourceNotFoundException("exception.cargo.not_found_by_id" + cargoId));

        log.info("Cargo with id = {} has been find ", cargoId);

        return cargoMapperDTO.toDto(cargoOffer);
    }

    @Transactional
    @Override
    public PageWrapper<CargoOfferDTO> findAll(Integer page, Integer size, CargoSearchCriteriaDTO cargoSearchCriteriaDTO) {

        log.info("There was a request to findAll cargo with page " + page + "and size" + size);

        Specification<CargoOffer> specification =
                Specification.where(CargoSpecifications
                        .notDeleted())
                        .and(CargoSpecifications.getAllCargoByCountryId(cargoSearchCriteriaDTO.getCountryId()));

        Pageable pageable =
                PageRequest.of(page, size);

        Page<CargoOffer> cargoPage =
                cargoRepository.findAll(specification, pageable);

        log.info("Method response posted to findAll cargo with page " + page + "and size " + size);

        return
                new PageWrapper<>(cargoMapperDTO
                        .toDtoList(cargoPage.toList()),
                        cargoPage.getTotalPages(),
                        cargoPage.getTotalElements());
    }


    private CargoOffer mapToCargo(CargoOfferReqDTO cargoOfferReqDTO) {
        CargoOffer cargoOffer = new CargoOffer();

        cargoOffer.setLoadingDate(cargoOfferReqDTO.getLoadingDate());
        cargoOffer.setUnloadingDate(cargoOfferReqDTO.getUnloadingDate());
        cargoOffer.setTemperatureMode(cargoOfferReqDTO.getTemperatureMode());
        cargoOffer.setDescription(cargoOfferReqDTO.getDescription());
        cargoOffer.setPayment(paymentMapperDTO.toEntity(cargoOfferReqDTO.getPayment()));
        cargoOffer.setCargoDimensions(cargoDimensionsMapperDTO.toEntity(cargoOfferReqDTO.getCargoDimensions()));
        cargoOffer.setLoadingLocation(loadingLocationMapperDTO.toEntity(cargoOfferReqDTO.getLoadingLocation()));
        cargoOffer.setUnloadingLocation(unLoadingLocationMapperDTO.toEntity(cargoOfferReqDTO.getUnloadingLocation()));

        for (Long id : cargoOfferReqDTO.getCargoStowageMethodIds()) {
            TruckBodyType truckBodyType = truckBodyTypeRepository.findById(id)
                    .orElseThrow(() -> new ResourceNotFoundException("TruckBodyType with id =" + id + " not found"));
            cargoOffer.getTruckBodyTypes().add(truckBodyType);
        }

        for (Long id : cargoOfferReqDTO.getCargoStowageMethodIds()) {
            CargoStowageMethod cargoStowageMethod = cargoStowageMethodRepository.findById(id)
                    .orElseThrow(() -> new ResourceNotFoundException("CargoStowageMethod with id =" + id + " not found"));
            cargoOffer.getCargoStowageMethods().add(cargoStowageMethod);
        }

        CargoType cargoType = cargoTypeRepository.findById(cargoOfferReqDTO.getCargoTypeId())
                .orElseThrow(() -> new ResourceNotFoundException("CargoType with id =" + cargoOfferReqDTO.getCargoTypeId() + " not found"));
        cargoOffer.setCargoType(cargoType);

        return cargoOffer;
    }
}