package by.iba.exchange.common.dto.mapper;

import by.iba.exchange.common.domain.LoadingPayload;
import by.iba.exchange.common.dto.LoadingPayloadDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class LoadingPayloadMapperDTO extends AbstractMapperDTO<LoadingPayload, LoadingPayloadDTO> {

    @Autowired
    public LoadingPayloadMapperDTO() {
        super(LoadingPayload.class, LoadingPayloadDTO.class);
    }
}
