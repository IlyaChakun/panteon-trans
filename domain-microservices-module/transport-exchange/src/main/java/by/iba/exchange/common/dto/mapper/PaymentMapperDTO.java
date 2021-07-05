package by.iba.exchange.common.dto.mapper;

import by.iba.common.mapper.core.SimpleAbstractMapper;
import by.iba.exchange.common.domain.Payment;
import by.iba.exchange.common.dto.PaymentResp;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class PaymentMapperDTO extends SimpleAbstractMapper<Payment, PaymentResp> {

    @Autowired
    public PaymentMapperDTO() {
        super(Payment.class, PaymentResp.class);
    }
}
