package by.iba.truck.dto;

import by.iba.common.dto.CommonAttributesDTO;
import by.iba.truck.domain.TruckDimensions;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class TruckOfferReqDTO extends CommonAttributesDTO {

    private Long truckBodyTypeId;

    private TruckDimensions truckDimensions;

}
