package by.iba.truck.service;

import by.iba.common.dto.PageWrapper;
import by.iba.truck.dto.TruckOfferDTO;
import by.iba.truck.dto.TruckOfferReqDTO;

public interface TruckService {

    TruckOfferDTO save(TruckOfferReqDTO truckOfferReqDTO);

    TruckOfferDTO findById(Long id);

    PageWrapper<TruckOfferDTO> findAll(final Integer page, final Integer size);

}
