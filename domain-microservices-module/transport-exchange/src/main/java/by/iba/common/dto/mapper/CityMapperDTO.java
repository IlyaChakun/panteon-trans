package by.iba.common.dto.mapper;

import by.iba.common.dto.CityDTO;
import by.iba.common.entity.City;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class CityMapperDTO extends AbstractMapperDTO<City, CityDTO> {

    @Autowired
    public CityMapperDTO() {
        super(City.class, CityDTO.class);
    }
}