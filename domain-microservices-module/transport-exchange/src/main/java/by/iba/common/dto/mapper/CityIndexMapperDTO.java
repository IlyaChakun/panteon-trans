package by.iba.common.dto.mapper;

import by.iba.common.entity.CityIndex;
import by.iba.common.dto.CityIndexDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class CityIndexMapperDTO extends AbstractMapperDTO<CityIndex, CityIndexDTO> {

    @Autowired
    public CityIndexMapperDTO() {
        super(CityIndex.class, CityIndexDTO.class);
    }
}