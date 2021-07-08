package by.iba.exchange.truck.dto;

import by.iba.common.dto.core.BaseAbstractReq;
import by.iba.exchange.common.domain.LoadingPayload;
import by.iba.exchange.common.domain.UnloadingPayload;
import by.iba.exchange.common.dto.CommonAttributesResp;
import by.iba.exchange.truck.domain.TruckDimensions;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.HashSet;
import java.util.Set;

@Getter
@Setter
@NoArgsConstructor
public class TruckOfferReq extends BaseAbstractReq {

    private Long truckBodyTypeId;

    private TruckDimensions truckDimensions;

    private Long carrierCompanyId;

    private Set<Long> cargoStowageMethodIds = new HashSet<>();

    private LoadingPayload loadingPayload;

    private UnloadingPayload unloadingPayload;
}
