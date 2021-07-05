package by.iba.exchange.cargo.service;

import by.iba.exchange.cargo.dto.CargoOfferResp;
import by.iba.exchange.cargo.dto.CargoOfferReqResp;
import by.iba.exchange.cargo.dto.CargoSearchCriteriaDTO;
import by.iba.common.dto.PageWrapper;

public interface CargoOfferService {

    CargoOfferResp save(CargoOfferReqResp cargoOfferReqDTO);

    void delete(Long cargoId);

    CargoOfferResp findById(Long cargoId);

    PageWrapper<CargoOfferResp> findAll(final Integer page, final Integer size, CargoSearchCriteriaDTO cargoSearchCriteriaDTO);
}
