package by.iba.request.dto;

import by.iba.common.dto.LoadingLocationDTO;
import by.iba.common.dto.UnLoadingLocationDTO;

public class TransportationRequestDTO {

    private LoadingLocationDTO loadingLocation;

    private UnLoadingLocationDTO unLoadingLocation;

    private Long cargoOfferId;

    private Long truckOfferId;
}
