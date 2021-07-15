package by.iba.exchange.truck.dto;

import by.iba.exchange.common.dto.DimensionsDTO;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.Valid;

@Getter
@Setter
@NoArgsConstructor
public class TruckDimensionsDTO {

    private Double carryingCapacity;//TODO VALIDATION

    @Valid
    private DimensionsDTO dimensions;

}
