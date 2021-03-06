package by.iba.exchange.common.domain;

import by.iba.common.domain.core.SimpleAbstractEntity;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "truck_types")
@Getter
@Setter
@NoArgsConstructor
public class TruckBodyType extends SimpleAbstractEntity {

    @Column(name = "truck_type_name")
    private String truckTypeName;

}
