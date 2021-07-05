package by.iba.exchange.request.dto.mapper;

import by.iba.common.mapper.core.SimpleAbstractMapper;
import by.iba.exchange.request.dto.TransportationRequestResp;
import by.iba.exchange.request.entity.TransportationRequest;
import org.springframework.stereotype.Component;

@Component
public class TransportationRequestMapperDTO extends SimpleAbstractMapper<TransportationRequest, TransportationRequestResp> {


    public TransportationRequestMapperDTO() {
        super(TransportationRequest.class, TransportationRequestResp.class);
    }

}
