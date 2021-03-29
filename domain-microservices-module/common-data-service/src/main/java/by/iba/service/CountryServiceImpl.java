package by.iba.service;


import by.iba.common.exception.ResourceNotFoundException;
import by.iba.dto.CountryDTO;
import by.iba.dto.mapper.CountryMapperDTO;
import by.iba.entity.Country;
import by.iba.repository.CountryRepository;
import by.iba.service.core.BaseSearchService;
import lombok.AllArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class CountryServiceImpl implements BaseSearchService<CountryDTO> {

    private static final Logger log = LoggerFactory.getLogger(CountryServiceImpl.class);

    private final CountryRepository countryRepository;
    private final CountryMapperDTO countryMapper;

    @Override
    @Cacheable("countries")
    public List<CountryDTO> findAll() {
        List<Country> countries = countryRepository.findAll();
        return countryMapper.toDtoList(countries);
    }

    @Override
    @Cacheable("country")
    public CountryDTO getOne(Long id) {
        Country country = findCountryById(id);
        return countryMapper.toDto(country);
    }

    private Country findCountryById(Long id) {
        return
                countryRepository.findById(id)
                        .orElseThrow(() -> {
                                    log.error("Country with id={} not found!", id);
                                    return new ResourceNotFoundException("Country with id=" + id + " not found!");
                                }
                        );
    }

    @Override
    public Boolean existById(Long id) {
        return countryRepository.existsById(id);
    }


}
