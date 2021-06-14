package by.iba.cargo.service;

import by.iba.cargo.domain.Cargo;
import by.iba.cargo.domain.CargoType;
import by.iba.cargo.dto.CargoDTO;
import by.iba.cargo.dto.mapper.CargoMapperDTO;
import by.iba.cargo.repository.CargoRepository;
import by.iba.cargo.repository.CargoTypeRepository;
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
import org.springframework.cache.annotation.Caching;
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
    @CachePut(value = "cargo_id", key = "#cargoDTO.getCargoTypeId()")
    @Override
    public CargoDTO save(CargoDTO cargoDTO) {

        log.info("Start saving the cargo with id = {}",
                cargoMapperDTO
                        .toEntity(cargoDTO)
                        .getId());

        Cargo cargo = cargoMapperDTO.toEntity(cargoDTO);

        for (Long id : cargoDTO.getCargoStowageMethodIds()) {
            CargoStowageMethod cargoStowageMethod = cargoStowageMethodRepository.findById(id)
                    .orElseThrow(() -> new ResourceNotFoundException("Stowage method now found"));
            cargo.getCargoStowageMethods().add(cargoStowageMethod);
        }

        for (Long id : cargoDTO.getTruckBodyTypeIds()) {
            TruckBodyType truckBodyType = truckBodyTypeRepository.findById(id)
                    .orElseThrow(() -> new ResourceNotFoundException("body type now found"));
            cargo.getTruckBodyTypes().add(truckBodyType);
        }

        CargoType cargoType = cargoTypeRepository.findById(cargoDTO.getCargoTypeId())
                .orElseThrow(() -> new ResourceNotFoundException("Cargo type now found"));
        cargo.setCargoType(cargoType);

        cargoRepository.save(cargo);

        log.info("Finish saving cargo with id = {}", cargoMapperDTO
                .toEntity(cargoDTO)
                .getId());

        return cargoMapperDTO.toDto(cargo);
    }

    @Override
    public CargoDTO update(Long cargoId, CargoDTO cargoDTO) {
        return null;
    }

    @Transactional
    @CacheEvict(value = "cargo_id", key = "#id")
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

    @Override
    public PageWrapper<CargoDTO> findAll(Integer page, Integer size) {
        return null;
    }


}
