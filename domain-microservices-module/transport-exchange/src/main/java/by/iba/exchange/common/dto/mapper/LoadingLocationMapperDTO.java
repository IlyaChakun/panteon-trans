package by.iba.exchange.common.dto.mapper;

import by.iba.exchange.common.domain.LoadingPayload;
import by.iba.exchange.common.dto.LoadingLocationDTO;
import by.iba.common.dto.mapper.AbstractMapperDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class LoadingLocationMapperDTO extends AbstractMapperDTO<LoadingPayload, LoadingLocationDTO> {

    @Autowired
    public LoadingLocationMapperDTO() {
        super(LoadingPayload.class, LoadingLocationDTO.class);
    }
}
