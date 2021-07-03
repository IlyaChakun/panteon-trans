package by.iba.common.dto.mapper;

import by.iba.common.dto.CountryDTO;
import by.iba.common.entity.Country;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class CountryMapperDTO extends AbstractMapperDTO<Country, CountryDTO> {

    @Autowired
    public CountryMapperDTO() {
        super(Country.class, CountryDTO.class);
    }
}