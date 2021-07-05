package by.iba.exchange.cargo.dto;

import by.iba.common.dto.core.BaseResp;
import by.iba.exchange.common.dto.DimensionsDTO;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


@Getter
@Setter
@NoArgsConstructor
public class CargoDimensionsResp extends BaseResp {

    private Double weight;

    private DimensionsDTO dimensions;

}
