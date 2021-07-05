package by.iba.exchange.request.dto.mapper;

import by.iba.common.mapper.core.SimpleAbstractMapper;
import by.iba.exchange.request.dto.CarrierResp;
import by.iba.exchange.request.entity.Carrier;
import org.springframework.stereotype.Component;

@Component
public class CarrierMapperDTO extends SimpleAbstractMapper<Carrier, CarrierResp> {

    public CarrierMapperDTO() {
        super(Carrier.class, CarrierResp.class);
    }

}
