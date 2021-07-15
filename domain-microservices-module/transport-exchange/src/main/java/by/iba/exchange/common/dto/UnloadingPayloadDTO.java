package by.iba.exchange.common.dto;

import by.iba.common.dto.AddressDTO;
import by.iba.common.dto.core.FullAbstractResp;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.Valid;
import java.time.LocalDate;


@Getter
@Setter
@NoArgsConstructor
public class UnloadingPayloadDTO extends FullAbstractResp {

    @Valid
    private AddressDTO address;

    private LocalDate unloadingDate;//TODO VALIDATION

}
