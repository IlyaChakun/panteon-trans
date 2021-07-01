package by.iba.dto.mapper;

import by.iba.common.dto.mapper.AbstractMapperDTO;
import by.iba.dto.CityIndexDTO;
import by.iba.entity.CityIndex;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class CityIndexMapperDTO extends AbstractMapperDTO<CityIndex, CityIndexDTO> {

    @Autowired
    public CityIndexMapperDTO() {
        super(CityIndex.class, CityIndexDTO.class);
    }
}