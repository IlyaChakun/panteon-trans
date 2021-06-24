package by.iba.common.dto.mapper;

import by.iba.common.domain.Payment;
import by.iba.common.dto.PaymentDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class PaymentMapperDTO extends AbstractMapperDTO<Payment, PaymentDTO> {

    @Autowired
    public PaymentMapperDTO() {
        super(Payment.class, PaymentDTO.class);
    }
}
