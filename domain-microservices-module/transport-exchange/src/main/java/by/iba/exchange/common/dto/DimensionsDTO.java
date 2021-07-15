package by.iba.exchange.common.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class DimensionsDTO {

    private Double volume;//TODO VALIDATION

    private Double length;

    private Double height;

}
