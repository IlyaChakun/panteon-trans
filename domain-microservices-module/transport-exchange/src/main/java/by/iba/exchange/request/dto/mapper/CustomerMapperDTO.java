package by.iba.exchange.request.dto.mapper;

import by.iba.common.mapper.core.SimpleAbstractMapper;
import by.iba.exchange.request.dto.CustomerResp;
import by.iba.exchange.request.entity.Customer;
import org.springframework.stereotype.Component;

@Component
public class CustomerMapperDTO extends SimpleAbstractMapper<Customer, CustomerResp> {

    public CustomerMapperDTO() {
        super(Customer.class, CustomerResp.class);
    }

}
