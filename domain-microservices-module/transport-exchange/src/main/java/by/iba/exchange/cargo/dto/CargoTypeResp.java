package by.iba.exchange.cargo.dto;

import by.iba.common.dto.core.SimpleAbstractResp;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class CargoTypeResp extends SimpleAbstractResp {

    private String typeName;
}
