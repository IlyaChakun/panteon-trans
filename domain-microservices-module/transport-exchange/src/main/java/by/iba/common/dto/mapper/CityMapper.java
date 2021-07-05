package by.iba.common.dto.mapper;

import by.iba.common.dto.CityResp;
import by.iba.common.entity.City;
import by.iba.common.mapper.core.SimpleAbstractMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class CityMapper extends SimpleAbstractMapper<City, CityResp> {

    @Autowired
    public CityMapper() {
        super(City.class, CityResp.class);
    }
}