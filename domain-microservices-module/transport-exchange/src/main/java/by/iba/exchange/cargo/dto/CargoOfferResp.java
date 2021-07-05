package by.iba.exchange.cargo.dto;

import by.iba.exchange.common.domain.TemperatureMode;
import by.iba.exchange.common.dto.CommonAttributesResp;
import by.iba.exchange.common.dto.TruckBodyTypeResp;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;

@Getter
@Setter
@NoArgsConstructor
public class CargoOfferResp extends CommonAttributesResp {

    private CargoTypeResp cargoType;

    private String description;

    private CargoDimensionsResp cargoDimensions;

    private Set<TruckBodyTypeResp> truckBodyTypes = new HashSet<>();

    private LocalDate deletionDate = null;

    private Long userId;

    private Long customerCompanyId;

    private TemperatureMode temperatureMode;

}

