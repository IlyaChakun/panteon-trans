package by.iba.cargo.dto;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class CargoSearchCriteriaDTO {

    private Long countryId;

    private Long regionId;

    private Long cityId;

    private CargoSearchCriteriaDTO getSearchCriteria(Long countryId, Long regionId, Long cityId) {
        CargoSearchCriteriaDTO cargoSearchCriteriaDTO = new CargoSearchCriteriaDTO();
        cargoSearchCriteriaDTO.setCityId(cityId);
        cargoSearchCriteriaDTO.setCountryId(countryId);
        cargoSearchCriteriaDTO.setRegionId(regionId);
        return cargoSearchCriteriaDTO;
    }

}
