package by.iba.exchange.truck.service;

import by.iba.common.dto.PageWrapper;
import by.iba.common.dto.PatchReq;
import by.iba.exchange.truck.dto.TruckOfferResp;
import by.iba.exchange.truck.dto.TruckOfferReq;

public interface TruckOfferService {

    TruckOfferResp save(TruckOfferReq truckOfferReqDTO);

    TruckOfferResp findById(Long id);

    PageWrapper<TruckOfferResp> findAll(final Integer page, final Integer size);

    TruckOfferResp partialUpdate(PatchReq patch, Long id);

}
