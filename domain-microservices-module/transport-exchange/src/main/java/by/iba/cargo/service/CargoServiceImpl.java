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
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
@Slf4j
public class CargoServiceImpl implements CargoService {

    private final CargoRepository cargoRepository;
    private final CargoMapperDTO cargoMapperDTO;
    private final CargoStowageMethodRepository cargoStowageMethodRepository;
    private final TruckBodyTypeRepository truckBodyTypeRepository;
    private final CargoTypeRepository cargoTypeRepository;

    @Override
    public CargoDTO save(CargoDTO cargoDTO) {
        Cargo cargo = cargoMapperDTO.toEntity(cargoDTO);

        for (Long id : cargoDTO.getCargoStowageMethodIds()) {
            CargoStowageMethod cargoStowageMethod = cargoStowageMethodRepository.findById(id)
                    .orElseThrow(() -> new ResourceNotFoundException("Stowage method now found"));
            cargo.getCargoStowageMethods().add(cargoStowageMethod);
        }

        for (Long id : cargoDTO.getTruckBodyTypeIds()) {
            TruckBodyType truckBodyType = truckBodyTypeRepository.findById(id)
                    .orElseThrow(() -> new ResourceNotFoundException("body type  now found"));
            cargo.getTruckBodyTypes().add(truckBodyType);
        }

        CargoType cargoType = cargoTypeRepository.findById(cargoDTO.getCargoTypeId())
                .orElseThrow(() -> new ResourceNotFoundException("Cargo type  now found"));
        cargo.setCargoType(cargoType);

        cargoRepository.save(cargo);
        return cargoMapperDTO.toDto(cargo);
    }

    @Override
    public CargoDTO update(Long cargoId, CargoDTO cargoDTO) {
        return null;
    }

    @Override
    public void delete(Long cargoId) {

    }

    @Override
    public CargoDTO findById(Long cargoId) {
        Cargo cargo = cargoRepository.findById(cargoId)
                .orElseThrow(() -> new ResourceNotFoundException("Cargo with id=" + cargoId + " not found!"));

        return cargoMapperDTO.toDto(cargo);
    }

    @Override
    public PageWrapper<CargoDTO> findAll(Integer page, Integer size) {
        return null;
    }


}
