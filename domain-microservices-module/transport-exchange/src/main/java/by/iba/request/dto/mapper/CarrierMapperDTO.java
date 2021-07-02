package by.iba.request.dto.mapper;

import by.iba.common.dto.mapper.AbstractMapperDTO;
import by.iba.request.dto.CarrierDTO;
import by.iba.request.entity.Carrier;
import org.springframework.stereotype.Component;

@Component
public class CarrierMapperDTO extends AbstractMapperDTO<Carrier, CarrierDTO> {

    public CarrierMapperDTO() {
        super(Carrier.class, CarrierDTO.class);
    }

}
