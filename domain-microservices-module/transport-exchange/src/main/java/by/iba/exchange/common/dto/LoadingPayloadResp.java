package by.iba.exchange.common.dto;

import by.iba.common.dto.core.BaseAbstractResp;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;


@Getter
@Setter
@NoArgsConstructor
public class LoadingPayloadResp extends BaseAbstractResp {

    private Long countryId;

    private Long regionId;

    private Long cityId;

    private LocalDate loadingDate;
}