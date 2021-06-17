package by.iba.cargo.dto;

import by.iba.common.dto.CommonAttributesDTO;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.HashSet;
import java.util.Set;

@Getter
@Setter
@NoArgsConstructor
public class CargoReqDTO extends CommonAttributesDTO {

    private Long cargoTypeId;

    private String description;

    private CargoDimensionsDTO cargoDimensions;

    private Set<Long> truckBodyTypeIds = new HashSet<>(); //типы кузова

}
