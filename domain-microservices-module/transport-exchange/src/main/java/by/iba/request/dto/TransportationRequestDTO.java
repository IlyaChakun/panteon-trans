package by.iba.request.dto;

import by.iba.common.dto.BaseDTO;
import by.iba.common.dto.LoadingPayloadDTO;
import by.iba.common.dto.UnloadingPayloadDTO;
import by.iba.request.entity.TransportationRequestStatus;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.NotNull;


@Getter
@Setter
@NoArgsConstructor
public class TransportationRequestDTO extends BaseDTO {

    @NotNull
    private Long id;

    @NotNull
    private CarrierDTO carrier;

    @NotNull
    private CustomerDTO customer;

    @NotNull
    private LoadingPayloadDTO loadingPayload;

    @NotNull
    private UnloadingPayloadDTO unloadingPayload;

    @NotNull
    private Long cargoOfferId;

    @NotNull
    private Long truckOfferId;

    @NotNull
    private Double freightCost;

    private String additionalInformation;

    private TransportationRequestStatus status;

}
