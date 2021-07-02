package by.iba.common.domain;

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
public class TruckBodyType extends BaseAbstractEntity {

    @Column(name = "truck_type_name")
    private String truckTypeName;

}
