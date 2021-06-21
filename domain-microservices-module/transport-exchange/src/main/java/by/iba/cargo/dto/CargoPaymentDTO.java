package by.iba.cargo.dto;

import by.iba.cargo.domain.Currency;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class CargoPaymentDTO {

    private Currency currency;

    private Double cost;

}
