package by.iba.cargo.service;

import by.iba.cargo.dto.CargoDTO;
import by.iba.common.dto.PageWrapper;

public interface CargoService {

    CargoDTO save(CargoDTO cargoDTO);
    CargoDTO update(String cargoId, CargoDTO cargoDTO);
    void delete(String cargoId);
    CargoDTO findById(String cargoId);
    PageWrapper<CargoDTO> findAll();
}
