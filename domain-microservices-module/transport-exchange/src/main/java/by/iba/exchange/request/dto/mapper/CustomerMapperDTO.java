package by.iba.exchange.request.dto.mapper;

import by.iba.common.dto.mapper.AbstractMapperDTO;
import by.iba.exchange.request.dto.CustomerDTO;
import by.iba.exchange.request.entity.Customer;
import org.springframework.stereotype.Component;

@Component
public class CustomerMapperDTO extends AbstractMapperDTO<Customer, CustomerDTO> {

    public CustomerMapperDTO() {
        super(Customer.class, CustomerDTO.class);
    }

}
