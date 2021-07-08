package by.iba.exchange.truck.dto;

import by.iba.exchange.common.dto.CommonAttributesResp;
import by.iba.exchange.common.dto.TruckBodyTypeResp;
import by.iba.exchange.truck.domain.TruckDimensions;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class TruckOfferResp extends CommonAttributesResp {//TODO change all to DTO model

    private TruckBodyTypeResp truckBodyType;

    private Long carrierCompanyId;

    private TruckDimensions truckDimensions;

}
