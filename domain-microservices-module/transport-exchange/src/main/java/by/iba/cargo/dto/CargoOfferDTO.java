package by.iba.cargo.dto;

import by.iba.common.domain.TemperatureMode;
import by.iba.common.dto.CommonAttributesDTO;
import by.iba.common.dto.TruckBodyTypeDTO;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;

@Getter
@Setter
@NoArgsConstructor
public class CargoOfferDTO extends CommonAttributesDTO {

    private CargoTypeDTO cargoType;

    private String description;

    private CargoDimensionsDTO cargoDimensions;

    private Set<TruckBodyTypeDTO> truckBodyTypes = new HashSet<>(); 

    private LocalDate deletionDate = null;

    private Long userId;

    private Long customerCompanyId;

    private TemperatureMode temperatureMode;

}

