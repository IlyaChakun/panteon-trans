package by.iba.common.dto.mapper;

import by.iba.common.dto.CountryResp;
import by.iba.common.entity.Country;
import by.iba.common.mapper.core.SimpleAbstractMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class CountryMapper extends SimpleAbstractMapper<Country, CountryResp> {

    @Autowired
    public CountryMapper() {
        super(Country.class, CountryResp.class);
    }
}