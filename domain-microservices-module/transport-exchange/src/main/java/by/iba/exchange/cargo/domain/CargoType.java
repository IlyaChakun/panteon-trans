package by.iba.exchange.cargo.domain;

import by.iba.common.domain.core.SimpleAbstractEntity;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "cargo_types")
@Getter
@Setter
@NoArgsConstructor
public class CargoType extends SimpleAbstractEntity {

    @Column(name = "type_name")
    private String typeName;

}
