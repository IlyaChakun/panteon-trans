package by.iba.exchange.cargo.service;

import by.iba.exchange.cargo.dto.CargoOfferDTO;
import by.iba.exchange.cargo.dto.CargoOfferReqDTO;
import by.iba.exchange.cargo.dto.CargoSearchCriteriaDTO;
import by.iba.common.dto.PageWrapper;

public interface CargoService {

    CargoOfferDTO save(CargoOfferReqDTO cargoOfferReqDTO);

    void delete(Long cargoId);

    CargoOfferDTO findById(Long cargoId);

    PageWrapper<CargoOfferDTO> findAll(final Integer page, final Integer size, CargoSearchCriteriaDTO cargoSearchCriteriaDTO);
}
