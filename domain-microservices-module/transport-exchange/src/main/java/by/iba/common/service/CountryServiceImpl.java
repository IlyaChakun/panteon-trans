package by.iba.common.service;


import by.iba.common.dto.CountryResp;
import by.iba.common.dto.mapper.CountryMapper;
import by.iba.common.exception.ResourceNotFoundException;
import by.iba.common.repository.CountryRepository;
import by.iba.common.entity.Country;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
@Slf4j
public class CountryServiceImpl implements CountryService {

    private final CountryRepository countryRepository;
    private final CountryMapper countryMapper;

    @Override
    @Cacheable("countries")
    public List<CountryResp> findAll() {
        

        List<Country> countries = countryRepository.findAll();
        return countryMapper.toDtoList(countries);
    }

    @Override
    @Cacheable("country")
    public CountryResp getOne(Long id) {
        

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
