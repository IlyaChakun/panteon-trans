package by.iba.exchange.cargo.service;

import by.iba.common.dto.PatchReq;
import by.iba.exchange.cargo.dto.CargoOfferResp;
import by.iba.exchange.cargo.dto.CargoOfferReq;
import by.iba.exchange.cargo.dto.CargoSearchCriteriaDTO;
import by.iba.common.dto.PageWrapper;

public interface CargoOfferService {

    CargoOfferResp save(CargoOfferReq cargoOfferReqDTO);

    void delete(Long cargoId);

    CargoOfferResp findById(Long cargoId);

    PageWrapper<CargoOfferResp> findAll(final Integer page, final Integer size, CargoSearchCriteriaDTO cargoSearchCriteriaDTO);

    CargoOfferResp  partialUpdate(PatchReq patch, Long id);
}
