package by.iba.exchange.cargo.dto;

import by.iba.common.dto.core.BaseAbstractResp;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class CargoTypeResp extends BaseAbstractResp {

    private String typeName;
}
