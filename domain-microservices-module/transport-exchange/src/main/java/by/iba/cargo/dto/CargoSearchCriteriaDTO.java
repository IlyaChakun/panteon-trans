package by.iba.cargo.dto;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class CargoSearchCriteriaDTO {

    private Long countryId;

    private Long regionId;

    private Long cityId;

}
