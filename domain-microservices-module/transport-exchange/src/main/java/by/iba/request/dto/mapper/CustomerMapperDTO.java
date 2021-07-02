package by.iba.request.dto.mapper;

import by.iba.common.dto.mapper.AbstractMapperDTO;
import by.iba.request.dto.CustomerDTO;
import by.iba.request.entity.Customer;
import org.springframework.stereotype.Component;

@Component
public class CustomerMapperDTO extends AbstractMapperDTO<Customer, CustomerDTO> {

    public CustomerMapperDTO() {
        super(Customer.class, CustomerDTO.class);
    }

}
