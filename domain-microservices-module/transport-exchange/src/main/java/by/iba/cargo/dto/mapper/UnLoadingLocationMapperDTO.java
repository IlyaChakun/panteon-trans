package by.iba.cargo.dto.mapper;

import by.iba.common.domain.UnLoadingLocation;
import by.iba.common.dto.UnLoadingLocationDTO;
import by.iba.common.dto.mapper.AbstractMapperDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class UnLoadingLocationMapperDTO extends AbstractMapperDTO<UnLoadingLocation, UnLoadingLocationDTO> {

    @Autowired
    public UnLoadingLocationMapperDTO() {
        super(UnLoadingLocation.class, UnLoadingLocationDTO.class);
    }
}
