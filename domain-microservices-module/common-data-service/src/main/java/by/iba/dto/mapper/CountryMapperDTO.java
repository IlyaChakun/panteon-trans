package by.iba.dto.mapper;

import by.iba.common.dto.mapper.AbstractMapperDTO;
import by.iba.dto.CountryDTO;
import by.iba.entity.Country;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class CountryMapperDTO extends AbstractMapperDTO<Country, CountryDTO> {

    @Autowired
    public CountryMapperDTO() {
        super(Country.class, CountryDTO.class);
    }
}