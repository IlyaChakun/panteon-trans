package by.iba.exchange.request.dto;

import by.iba.exchange.common.dto.LoadingLocationDTO;
import by.iba.exchange.common.dto.UnLoadingLocationDTO;

public class TransportationRequestDTO {

    private LoadingLocationDTO loadingLocation;

    private UnLoadingLocationDTO unLoadingLocation;

    private Long cargoOfferId;

    private Long truckOfferId;
}
