package by.iba.exchange.truck.service;

import by.iba.common.dto.PageWrapper;
import by.iba.exchange.truck.dto.TruckOfferResp;
import by.iba.exchange.truck.dto.TruckOfferReqResp;

public interface TruckOfferService {

    TruckOfferResp save(TruckOfferReqResp truckOfferReqDTO);

    TruckOfferResp findById(Long id);

    PageWrapper<TruckOfferResp> findAll(final Integer page, final Integer size);

}
