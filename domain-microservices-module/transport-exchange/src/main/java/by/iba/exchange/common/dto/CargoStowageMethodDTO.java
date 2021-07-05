package by.iba.exchange.common.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Column;

@Getter
@Setter
@NoArgsConstructor
public class CargoStowageMethodDTO extends BaseAbstractDTO {//способ погрузки

    @Column(name = "stowage_method_name")
    private String stowageMethodName;

}
