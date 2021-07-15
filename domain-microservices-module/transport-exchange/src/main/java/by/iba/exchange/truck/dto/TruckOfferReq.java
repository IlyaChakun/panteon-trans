package by.iba.exchange.truck.dto;

import by.iba.common.dto.core.BaseAbstractReq;
import by.iba.exchange.common.dto.LoadingPayloadDTO;
import by.iba.exchange.common.dto.UnloadingPayloadDTO;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.Valid;
import java.util.HashSet;
import java.util.Set;

@Getter
@Setter
@NoArgsConstructor
public class TruckOfferReq extends BaseAbstractReq {

    private Long truckBodyTypeId;//TODO Validation

    @Valid
    private TruckDimensionsDTO truckDimensions;

    private Long carrierCompanyId;

    private Set<Long> cargoStowageMethodIds = new HashSet<>();

    @Valid
    private LoadingPayloadDTO loadingPayload;

    @Valid
    private UnloadingPayloadDTO unloadingPayload;
}
