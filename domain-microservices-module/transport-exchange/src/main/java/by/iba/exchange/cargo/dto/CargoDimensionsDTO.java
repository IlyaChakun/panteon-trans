package by.iba.exchange.cargo.dto;

import by.iba.common.dto.core.MappableObjectDTO;
import by.iba.exchange.common.dto.DimensionsDTO;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.Valid;


@Getter
@Setter
@NoArgsConstructor
public class CargoDimensionsDTO extends MappableObjectDTO {

    private Double weight;//TODO VALIDATION

    @Valid
    private DimensionsDTO dimensions;

}
