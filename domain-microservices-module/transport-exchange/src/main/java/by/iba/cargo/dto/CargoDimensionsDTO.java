package by.iba.cargo.dto;

import by.iba.common.dto.BaseDTO;
import by.iba.common.dto.DimensionsDTO;
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
