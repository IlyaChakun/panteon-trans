package by.iba.exchange.common.dto.mapper;

import by.iba.common.mapper.core.SimpleAbstractMapper;
import by.iba.exchange.common.domain.LoadingPayload;
import by.iba.exchange.common.dto.LoadingPayloadDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class LoadingPayloadMapper extends SimpleAbstractMapper<LoadingPayload, LoadingPayloadDTO> {

    @Autowired
    public LoadingPayloadMapper() {
        super(LoadingPayload.class, LoadingPayloadDTO.class);
    }
}
