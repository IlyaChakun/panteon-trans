package by.iba.truck.dto;

import by.iba.common.dto.CommonAttributesDTO;
import by.iba.common.dto.TruckBodyTypeDTO;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class TruckOfferDTO extends CommonAttributesDTO {//TODO change all to DTO model

    private TruckBodyTypeDTO truckBodyType;

}
