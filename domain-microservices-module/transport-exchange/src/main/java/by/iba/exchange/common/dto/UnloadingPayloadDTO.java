package by.iba.exchange.common.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;


@Getter
@Setter
@NoArgsConstructor
public class UnloadingPayloadDTO extends BaseAbstractDTO {

    private Long countryId;

    private Long regionId;

    private Long cityId;

    private LocalDate unloadingDate;
}
