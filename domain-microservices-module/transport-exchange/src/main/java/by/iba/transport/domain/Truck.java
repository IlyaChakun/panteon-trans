package by.iba.transport.domain;

import by.iba.common.domain.CommonAttributes;
import by.iba.common.domain.TruckBodyType;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "trucks")
@Getter
@Setter
@NoArgsConstructor
public class Truck extends CommonAttributes {

    @ManyToOne(cascade = CascadeType.ALL)
    private TruckBodyType truckBodyType;

    private TruckDimensions truckDimensions;

}
