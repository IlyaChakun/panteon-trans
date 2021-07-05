package by.iba.exchange.common.dto.mapper;

import by.iba.common.mapper.core.SimpleAbstractMapper;
import by.iba.exchange.common.domain.LoadingPayload;
import by.iba.exchange.common.dto.LoadingPayloadResp;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class LoadingPayloadMapperDTO extends SimpleAbstractMapper<LoadingPayload, LoadingPayloadResp> {

    @Autowired
    public LoadingPayloadMapperDTO() {
        super(LoadingPayload.class, LoadingPayloadResp.class);
    }
}
