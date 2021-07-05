package by.iba.exchange.cargo.dto;

import by.iba.exchange.common.dto.BaseDTO;
import by.iba.exchange.common.dto.DimensionsDTO;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


@Getter
@Setter
@NoArgsConstructor
public class CargoDimensionsDTO extends BaseDTO {

    private Double weight;

    private DimensionsDTO dimensions;

}
