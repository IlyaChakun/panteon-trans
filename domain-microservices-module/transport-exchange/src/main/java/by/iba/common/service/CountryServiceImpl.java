package by.iba.common.service;


import by.iba.common.dto.CountryDTO;
import by.iba.common.dto.mapper.CountryMapperDTO;
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
    private final CountryMapperDTO countryMapper;

    @Override
    @Cacheable("countries")
    public List<CountryDTO> findAll() {
        log.info("find all countries");

        List<Country> countries = countryRepository.findAll();
        return countryMapper.toDtoList(countries);
    }

    @Override
    @Cacheable("country")
    public CountryDTO getOne(Long id) {
        log.info("get one country by id={}", id);

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
        log.info("exist country by id={}", id);
        return countryRepository.existsById(id);
    }


}