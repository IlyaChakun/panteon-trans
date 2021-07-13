package by.iba.exchange.common.dto;

import by.iba.common.dto.core.FullAbstractResp;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;


@Getter
@Setter
@NoArgsConstructor
public class LoadingPayloadResp extends FullAbstractResp {

    private Long countryId;

    private Long regionId;

    private Long cityId;

    private LocalDate loadingDate;
}
