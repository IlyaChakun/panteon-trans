package by.iba.exchange.request.dto.mapper;

import by.iba.exchange.common.dto.mapper.AbstractMapperDTO;
import by.iba.exchange.request.dto.CarrierDTO;
import by.iba.exchange.request.entity.Carrier;
import org.springframework.stereotype.Component;

@Component
public class CarrierMapperDTO extends AbstractMapperDTO<Carrier, CarrierDTO> {

    public CarrierMapperDTO() {
        super(Carrier.class, CarrierDTO.class);
    }

}
