package by.iba.cargo.domain;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.persistence.Enumerated;

@Embeddable
@Getter
@Setter
@NoArgsConstructor
public class CargoPayment {

    @Column(name = "currency")
    @Enumerated
    private Currency currency;

    @Column(name = "cost")
    private Double cost;

}
