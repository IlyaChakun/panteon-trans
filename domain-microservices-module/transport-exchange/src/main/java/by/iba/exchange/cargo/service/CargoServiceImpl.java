package by.iba.exchange.cargo.service;

import by.iba.exchange.cargo.domain.CargoOffer;
import by.iba.exchange.cargo.domain.CargoType;
import by.iba.exchange.cargo.dto.CargoOfferDTO;
import by.iba.exchange.cargo.dto.CargoOfferReqDTO;
import by.iba.exchange.cargo.dto.CargoSearchCriteriaDTO;
import by.iba.exchange.cargo.dto.mapper.CargoDimensionsMapperDTO;
import by.iba.exchange.cargo.dto.mapper.CargoMapperDTO;
import by.iba.exchange.cargo.mail.CargoMailServiceImpl;
import by.iba.exchange.cargo.repository.CargoRepository;
import by.iba.exchange.cargo.repository.CargoTypeRepository;
import by.iba.exchange.cargo.specifications.CargoSpecifications;
import by.iba.exchange.common.domain.CargoStowageMethod;
import by.iba.exchange.common.domain.TruckBodyType;
import by.iba.exchange.common.dto.PageWrapper;
import by.iba.exchange.common.dto.mapper.LoadingPayloadMapperDTO;
import by.iba.exchange.common.dto.mapper.PaymentMapperDTO;
import by.iba.exchange.common.dto.mapper.UnloadingPayloadMapperDTO;
import by.iba.exchange.common.exception.ResourceNotFoundException;
import by.iba.exchange.common.repository.CargoStowageMethodRepository;
import by.iba.exchange.common.repository.TruckBodyTypeRepository;
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

    private final CargoDimensionsMapperDTO cargoDimensionsMapper;
    private final CargoMailServiceImpl cargoMailService;
    private final LoadingPayloadMapperDTO loadingLocationMapper;
    private final UnloadingPayloadMapperDTO unLoadingLocationMapper;
    private final CargoRepository cargoRepository;
    private final CargoMapperDTO cargoMapper;
    private final CargoStowageMethodRepository cargoStowageMethodRepository;
    private final TruckBodyTypeRepository truckBodyTypeRepository;
    private final CargoTypeRepository cargoTypeRepository;
    private final PaymentMapperDTO paymentMapper;
    private final UserRepository userRepository;


    @Transactional
    @CachePut(value = "id", key = "#p0")
    @Override
    public CargoOfferDTO save(CargoOfferReqDTO cargoOfferReqDTO) {
        log.info("Start saving the cargo");
        String email = userRepository.findByUserId(cargoOfferReqDTO.getUserId()).getEmail();
        CargoOffer cargoOffer = mapToCargo(cargoOfferReqDTO);
        CargoOffer savedCargoOffer = cargoRepository.save(cargoOffer);
        cargoMailService.sendSaveCargoNotification(email);
        log.info("Finish saving cargo with id =" + savedCargoOffer.getId());
        return cargoMapper.toDto(savedCargoOffer);
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

        return cargoMapper.toDto(cargoOffer);
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
                new PageWrapper<>(cargoMapper
                        .toDtoList(cargoPage.toList()),
                        cargoPage.getTotalPages(),
                        cargoPage.getTotalElements());
    }


    private CargoOffer mapToCargo(CargoOfferReqDTO cargoOfferReqDTO) {
        CargoOffer cargoOffer = new CargoOffer();

        cargoOffer.setCustomerCompanyId(cargoOfferReqDTO.getCustomerCompanyId());
        cargoOffer.setLoadingPayload(loadingLocationMapper.toEntity(cargoOfferReqDTO.getLoadingPayload()));
        cargoOffer.setUnloadingPayload(unLoadingLocationMapper.toEntity(cargoOfferReqDTO.getUnloadingPayload()));
        cargoOffer.setTemperatureMode(cargoOfferReqDTO.getTemperatureMode());
        cargoOffer.setDescription(cargoOfferReqDTO.getDescription());
        cargoOffer.setPayment(paymentMapper.toEntity(cargoOfferReqDTO.getPayment()));
        cargoOffer.setCargoDimensions(cargoDimensionsMapper.toEntity(cargoOfferReqDTO.getCargoDimensions()));
        cargoOffer.setLoadingPayload(loadingLocationMapper.toEntity(cargoOfferReqDTO.getLoadingPayload()));
        cargoOffer.setUnloadingPayload(unLoadingLocationMapper.toEntity(cargoOfferReqDTO.getUnloadingPayload()));

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