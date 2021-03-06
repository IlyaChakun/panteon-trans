package by.iba.exchange.cargo.domain;

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
public class CargoDimensions {

    @Column(name = "weight")
    private Double weight;

    private Dimensions dimensions;

}
