package by.iba.exchange.truck.domain;

import by.iba.exchange.common.domain.Dimensions;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Embeddable;

@Embeddable
@Getter
@Setter
@NoArgsConstructor
public class TruckDimensions {

    @Column(name = "carrying_capacity")
    private Double carryingCapacity;//Грузоподъемность

    private Dimensions dimensions;

}
