package by.iba.common.dto.mapper;

import by.iba.common.domain.UnLoadingPayload;
import by.iba.common.dto.UnLoadingLocationDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class UnLoadingLocationMapperDTO extends AbstractMapperDTO<UnLoadingPayload, UnLoadingLocationDTO> {

    @Autowired
    public UnLoadingLocationMapperDTO() {
        super(UnLoadingPayload.class, UnLoadingLocationDTO.class);
    }
}
