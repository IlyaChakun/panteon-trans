package by.iba.exchange.common.dto;

import by.iba.exchange.common.domain.Currency;
import by.iba.exchange.common.domain.PaymentType;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Embeddable;

@Embeddable
@Getter
@Setter
@NoArgsConstructor
public class PaymentDTO extends BaseDTO {

    private Currency currency;

    private Double cost;

    private PaymentType paymentType;
}
