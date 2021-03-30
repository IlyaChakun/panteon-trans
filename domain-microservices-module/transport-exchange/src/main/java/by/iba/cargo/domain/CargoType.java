package by.iba.cargo.domain;

import by.iba.common.domain.BaseAbstractEntity;
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
public class CargoType extends BaseAbstractEntity {

    @Column(name = "type_name")
    private String typeName;

}
