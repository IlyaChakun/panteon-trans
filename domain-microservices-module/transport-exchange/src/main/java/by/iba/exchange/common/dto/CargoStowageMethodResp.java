package by.iba.exchange.common.dto;

import by.iba.common.dto.core.BaseAbstractResp;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Column;

@Getter
@Setter
@NoArgsConstructor
public class CargoStowageMethodResp extends BaseAbstractResp {//способ погрузки

    @Column(name = "stowage_method_name")
    private String stowageMethodName;

}
