package by.iba.cargo.service;

import by.iba.cargo.dto.CargoDTO;
import by.iba.cargo.dto.CargoReqDTO;
import by.iba.cargo.dto.CargoSearchCriteriaDTO;
import by.iba.common.dto.PageWrapper;
import by.iba.domain.User;

public interface CargoService {

    CargoDTO save(CargoReqDTO cargoReqDTO, User user);

    CargoDTO update(Long cargoId, CargoReqDTO cargoReqDTO);

    void delete(Long cargoId);

    CargoDTO findById(Long cargoId);

    PageWrapper<CargoDTO> findAll(final Integer page, final Integer size, CargoSearchCriteriaDTO cargoSearchCriteriaDTO);
}
