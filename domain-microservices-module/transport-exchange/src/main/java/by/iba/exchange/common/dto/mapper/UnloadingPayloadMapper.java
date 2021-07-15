package by.iba.exchange.common.dto.mapper;

import by.iba.common.mapper.core.SimpleAbstractMapper;
import by.iba.exchange.common.domain.UnloadingPayload;
import by.iba.exchange.common.dto.UnloadingPayloadDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class UnloadingPayloadMapper extends SimpleAbstractMapper<UnloadingPayload, UnloadingPayloadDTO> {

    @Autowired
    public UnloadingPayloadMapper() {
        super(UnloadingPayload.class, UnloadingPayloadDTO.class);
    }
}
