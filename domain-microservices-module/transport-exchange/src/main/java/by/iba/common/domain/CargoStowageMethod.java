package by.iba.common.domain;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "cargo_stowage_methods")
@Getter
@Setter
@NoArgsConstructor
public class CargoStowageMethod extends BaseAbstractEntity {//способ погрузки

    @Column(name = "stowage_method_name")
    private String stowageMethodName;

    //TODO
    /**
     * полная
     *  частичная
     *  верхняя
     *  боковая
     *  задняя TIR
     *  по декларации
     *  ADR
     *  лифт
     *  манипулятор
     */

}
