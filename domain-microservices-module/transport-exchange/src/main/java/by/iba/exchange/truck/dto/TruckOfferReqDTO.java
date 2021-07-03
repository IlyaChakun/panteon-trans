package by.iba.exchange.truck.dto;

import by.iba.exchange.common.dto.CommonAttributesDTO;
import by.iba.exchange.truck.domain.TruckDimensions;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class TruckOfferReqDTO extends CommonAttributesDTO {

    private Long truckBodyTypeId;

    private TruckDimensions truckDimensions;

    private Long carrierCompanyId;
}
