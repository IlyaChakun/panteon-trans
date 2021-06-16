package by.iba.cargo.service;

import by.iba.cargo.domain.Cargo;
import by.iba.cargo.domain.CargoDimensions;
import by.iba.cargo.domain.CargoType;
import by.iba.cargo.dto.CargoDTO;
import by.iba.cargo.dto.CargoSearchCriteriaDTO;
import by.iba.cargo.dto.mapper.CargoMapperDTO;
import by.iba.cargo.repository.CargoRepository;
import by.iba.cargo.repository.CargoTypeRepository;
import by.iba.cargo.specifications.CargoSpecifications;
import by.iba.common.domain.CargoStowageMethod;
import by.iba.common.domain.TruckBodyType;
import by.iba.common.dto.PageWrapper;
import by.iba.common.exception.ResourceNotFoundException;
import by.iba.common.repository.CargoStowageMethodRepository;
import by.iba.common.repository.TruckBodyTypeRepository;
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

    private final CargoRepository cargoRepository;
    private final CargoMapperDTO cargoMapperDTO;
    private final CargoStowageMethodRepository cargoStowageMethodRepository;
    private final TruckBodyTypeRepository truckBodyTypeRepository;
    private final CargoTypeRepository cargoTypeRepository;

    @Transactional
    @CachePut(value = "id", key = "#p0")
    @Override
    public CargoDTO save(CargoDTO cargoDTO) {

        log.info("Start saving the cargo with id = {}",
                cargoMapperDTO
                        .toEntity(cargoDTO)
                        .getId());

        Cargo cargo = cargoMapperDTO.toEntity(cargoDTO);

        for (Long id : cargoDTO.getCargoStowageMethodIds()) {
            CargoStowageMethod cargoStowageMethod = cargoStowageMethodRepository.findById(id)
                    .orElseThrow(() -> new ResourceNotFoundException("CargoStowageMethod with id = " + id + " not found"));
            cargo.getCargoStowageMethods().add(cargoStowageMethod);
        }

        for (Long id : cargoDTO.getTruckBodyTypeIds()) {
            TruckBodyType truckBodyType = truckBodyTypeRepository.findById(id)
                    .orElseThrow(() -> new ResourceNotFoundException("TruckBodyType with id =" + id + " not found"));
            cargo.getTruckBodyTypes().add(truckBodyType);
        }

        CargoType cargoType = cargoTypeRepository.findById(cargoDTO.getCargoTypeId())
                .orElseThrow(() -> new ResourceNotFoundException("CargoType with id =" + cargoDTO.getCargoTypeId() + " not found"));
        cargo.setCargoType(cargoType);

        cargoRepository.save(cargo);

        log.info("Finish saving cargo with id = {}", cargoMapperDTO
                .toEntity(cargoDTO)
                .getId());

        return cargoMapperDTO.toDto(cargo);
    }

    @Transactional
    @CachePut(value = "id", key = "#p0")
    @Override
    public CargoDTO update(Long cargoId, CargoDTO cargoDTO) {
        log.info("Start update cargo with id = {} ", cargoId);

        Cargo cargoFromDTO = cargoMapperDTO.toEntity(cargoDTO);
        Cargo cargo = cargoRepository
                .findById(cargoId)
                .orElseThrow(() -> new ResourceNotFoundException("cargo with id = " + cargoId + " not found"));

        for (Long id : cargoDTO.getCargoStowageMethodIds()) {
            TruckBodyType truckBodyType = truckBodyTypeRepository.findById(id)
                    .orElseThrow(() -> new ResourceNotFoundException("TruckBodyType with id =" + id + " not found"));
            cargo.getTruckBodyTypes().add(truckBodyType);
        }

        for (Long id : cargoDTO.getCargoStowageMethodIds()) {
            CargoStowageMethod cargoStowageMethod = cargoStowageMethodRepository.findById(id)
                    .orElseThrow(() -> new ResourceNotFoundException("CargoStowageMethod with id =" + id + " not found"));
            cargo.getCargoStowageMethods().add(cargoStowageMethod);
        }

        CargoType cargoType = cargoTypeRepository.findById(cargoDTO.getCargoTypeId())
                .orElseThrow(() -> new ResourceNotFoundException("CargoType with id =" + cargoDTO.getCargoTypeId() + " not found"));
        cargo.setCargoType(cargoType);

        cargo.setCargoStowageMethods(cargoFromDTO.getCargoStowageMethods());
        cargo.setTruckBodyTypes(cargoFromDTO.getTruckBodyTypes());
        cargo.setCargoType(cargoFromDTO.getCargoType());
        cargo.setCargoDimensions(cargoFromDTO.getCargoDimensions());
        cargo.setDescription(cargoFromDTO.getDescription());
        cargo.setLoadingLocation(cargoFromDTO.getLoadingLocation());
        cargo.setUnloadingLocation(cargoFromDTO.getUnloadingLocation());
        Cargo saveCargo = cargoRepository.save(cargo);

        log.info("Finish update cargo with id = {}", saveCargo);

        return cargoMapperDTO.toDto(saveCargo);
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
                .orElseThrow(() -> new ResourceNotFoundException("Cargo with id=" + cargoId + " not found!"));

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
}
