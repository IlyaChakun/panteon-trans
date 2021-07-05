package by.iba.exchange.common.dto.mapper;

import by.iba.common.mapper.core.SimpleAbstractMapper;
import by.iba.exchange.common.domain.UnloadingPayload;
import by.iba.exchange.common.dto.UnloadingPayloadResp;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class UnloadingPayloadMapperDTO extends SimpleAbstractMapper<UnloadingPayload, UnloadingPayloadResp> {

    @Autowired
    public UnloadingPayloadMapperDTO() {
        super(UnloadingPayload.class, UnloadingPayloadResp.class);
    }
}
