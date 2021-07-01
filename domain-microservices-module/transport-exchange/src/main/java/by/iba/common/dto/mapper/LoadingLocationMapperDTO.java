package by.iba.common.dto.mapper;

import by.iba.common.domain.LoadingLocation;
import by.iba.common.dto.LoadingLocationDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class LoadingLocationMapperDTO extends AbstractMapperDTO<LoadingLocation, LoadingLocationDTO> {

    @Autowired
    public LoadingLocationMapperDTO() {
        super(LoadingLocation.class, LoadingLocationDTO.class);
    }
}
