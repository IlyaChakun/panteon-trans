package by.iba.exchange.common.dto;

import by.iba.common.dto.AddressDTO;
import by.iba.common.dto.core.FullAbstractResp;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;


@Getter
@Setter
@NoArgsConstructor
public class LoadingPayloadDTO extends FullAbstractResp {

    private AddressDTO address;

    private LocalDate loadingDate;
}
