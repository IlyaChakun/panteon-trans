package by.iba.exchange.common.dto;

import by.iba.common.dto.core.SimpleAbstractResp;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class TruckBodyTypeResp extends SimpleAbstractResp {

    private String truckTypeName;
}
