package by.iba.exchange.cargo.dto;

import by.iba.exchange.common.domain.TemperatureMode;
import by.iba.exchange.common.dto.CommonAttributesDTO;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.HashSet;
import java.util.Set;

@Getter
@Setter
@NoArgsConstructor
public class CargoOfferReqDTO extends CommonAttributesDTO {

    private Long cargoTypeId;

    private String description;

    private CargoDimensionsDTO cargoDimensions;

    private Set<Long> truckBodyTypeIds = new HashSet<>();

    private Long userId;

    private Long customerCompanyId;

    private TemperatureMode temperatureMode;

}
