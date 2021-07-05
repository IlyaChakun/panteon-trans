package by.iba.exchange.common.dto.mapper;

import by.iba.common.dto.mapper.AbstractMapperDTO;
import by.iba.exchange.common.domain.UnloadingPayload;
import by.iba.exchange.common.dto.UnloadingPayloadDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class UnloadingPayloadMapperDTO extends AbstractMapperDTO<UnloadingPayload, UnloadingPayloadDTO> {

    @Autowired
    public UnloadingPayloadMapperDTO() {
        super(UnloadingPayload.class, UnloadingPayloadDTO.class);
    }
}
