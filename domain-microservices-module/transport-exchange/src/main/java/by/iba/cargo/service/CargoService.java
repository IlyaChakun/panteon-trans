package by.iba.cargo.service;

import by.iba.cargo.dto.CargoDTO;
import by.iba.cargo.dto.CargoSearchCriteriaDTO;
import by.iba.common.dto.PageWrapper;

public interface CargoService {

    CargoDTO save(CargoDTO cargoDTO);

    CargoDTO update(Long cargoId, CargoDTO cargoDTO);

    void delete(Long cargoId);

    CargoDTO findById(Long cargoId);

    PageWrapper<CargoDTO> findAll(final Integer page, final Integer size, CargoSearchCriteriaDTO cargoSearchCriteriaDTO);
}
