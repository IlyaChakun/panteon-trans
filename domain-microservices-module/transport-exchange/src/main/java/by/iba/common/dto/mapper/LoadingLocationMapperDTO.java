package by.iba.common.dto.mapper;

import by.iba.common.domain.LoadingPayload;
import by.iba.common.dto.LoadingLocationDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class LoadingLocationMapperDTO extends AbstractMapperDTO<LoadingPayload, LoadingLocationDTO> {

    @Autowired
    public LoadingLocationMapperDTO() {
        super(LoadingPayload.class, LoadingLocationDTO.class);
    }
}
