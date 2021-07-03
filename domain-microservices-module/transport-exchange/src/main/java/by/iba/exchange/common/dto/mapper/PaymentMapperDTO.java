package by.iba.exchange.common.dto.mapper;

import by.iba.exchange.common.domain.Payment;
import by.iba.exchange.common.dto.PaymentDTO;
import by.iba.common.dto.mapper.AbstractMapperDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class PaymentMapperDTO extends AbstractMapperDTO<Payment, PaymentDTO> {

    @Autowired
    public PaymentMapperDTO() {
        super(Payment.class, PaymentDTO.class);
    }
}
