package by.iba.cargo.service;

import by.iba.cargo.dto.CargoDTO;
import by.iba.cargo.dto.mapper.CargoMapperDTO;
import by.iba.cargo.repository.CargoRepository;
import by.iba.common.dto.PageWrapper;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
@Slf4j
public class CargoServiceImpl implements CargoService{

    private final CargoRepository cargoRepository;
    private final CargoMapperDTO cargoMapperDTO;

    @Override
    public CargoDTO save(CargoDTO cargoDTO) {
        cargoRepository.save(cargoMapperDTO.toEntity(cargoDTO));
        return cargoDTO;
    }

    @Override
    public CargoDTO update(String cargoId, CargoDTO cargoDTO) {
        return null;
    }

    @Override
    public void delete(String cargoId) {

    }

    @Override
    public CargoDTO findById(String cargoId) {
        return null;
    }

    @Override
    public PageWrapper<CargoDTO> findAll() {
        //cargoRepository.findAll();
        return null;
    }
}
