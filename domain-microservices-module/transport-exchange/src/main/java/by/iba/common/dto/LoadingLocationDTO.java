package by.iba.common.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


@Getter
@Setter
@NoArgsConstructor
public class LoadingLocationDTO extends BaseAbstractDTO {

    private Long countryId;

    private Long regionId;

    private Long cityId;

}
