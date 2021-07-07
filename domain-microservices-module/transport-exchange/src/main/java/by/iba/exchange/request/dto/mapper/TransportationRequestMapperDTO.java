package by.iba.exchange.request.dto.mapper;

import by.iba.common.mapper.core.SimpleAbstractMapper;
import by.iba.exchange.request.dto.TransportationReqResp;
import by.iba.exchange.request.entity.TransportationReq;
import org.springframework.stereotype.Component;

@Component
public class TransportationRequestMapperDTO extends SimpleAbstractMapper<TransportationReq, TransportationReqResp> {


    public TransportationRequestMapperDTO() {
        super(TransportationReq.class, TransportationReqResp.class);
    }

}
