package by.iba.exchange.request.dto;

import by.iba.common.dto.core.BaseResp;
import by.iba.exchange.common.dto.LoadingPayloadResp;
import by.iba.exchange.common.dto.UnloadingPayloadResp;
import by.iba.exchange.request.entity.TransportationRequestStatus;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.NotNull;


@Getter
@Setter
@NoArgsConstructor
public class TransportationRequestResp extends BaseResp {

    @NotNull
    private Long id;

    @NotNull
    private CarrierResp carrier;

    @NotNull
    private CustomerResp customer;

    @NotNull
    private LoadingPayloadResp loadingPayload;

    @NotNull
    private UnloadingPayloadResp unloadingPayload;

    @NotNull
    private Long cargoOfferId;

    @NotNull
    private Long truckOfferId;

    @NotNull
    private Double freightCost;

    private String additionalInformation;

    private TransportationRequestStatus status;

}
