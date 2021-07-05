package by.iba.exchange.truck.service;

import by.iba.common.dto.PageWrapper;
import by.iba.exchange.truck.dto.TruckOfferDTO;
import by.iba.exchange.truck.dto.TruckOfferReqDTO;

public interface TruckOfferService {

    TruckOfferDTO save(TruckOfferReqDTO truckOfferReqDTO);

    TruckOfferDTO findById(Long id);

    PageWrapper<TruckOfferDTO> findAll(final Integer page, final Integer size);

}
