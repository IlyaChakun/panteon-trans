package by.iba.exchange.common.dto;

import by.iba.common.dto.core.BaseAbstractResp;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class TruckBodyTypeResp extends BaseAbstractResp {

    private String truckTypeName;
}
