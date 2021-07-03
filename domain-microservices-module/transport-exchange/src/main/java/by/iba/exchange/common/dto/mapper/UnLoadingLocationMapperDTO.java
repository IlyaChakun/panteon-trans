package by.iba.exchange.common.dto.mapper;

import by.iba.exchange.common.domain.UnLoadingPayload;
import by.iba.exchange.common.dto.UnLoadingLocationDTO;
import by.iba.common.dto.mapper.AbstractMapperDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class UnLoadingLocationMapperDTO extends AbstractMapperDTO<UnLoadingPayload, UnLoadingLocationDTO> {

    @Autowired
    public UnLoadingLocationMapperDTO() {
        super(UnLoadingPayload.class, UnLoadingLocationDTO.class);
    }
}
