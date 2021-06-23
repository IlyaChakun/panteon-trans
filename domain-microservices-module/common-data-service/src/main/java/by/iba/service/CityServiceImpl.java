package by.iba.service;


import by.iba.common.exception.ResourceNotFoundException;
import by.iba.dto.CityDTO;
import by.iba.dto.mapper.CityMapperDTO;
import by.iba.entity.City;
import by.iba.repository.CityIndexRepository;
import by.iba.repository.CityRepository;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
@Slf4j
public class CityServiceImpl implements CityService {

    private final CityRepository cityRepository;
    private final CityMapperDTO cityMapper;
    private final CityIndexRepository cityIndexRepository;

    @Override
    @Cacheable("cities")
    public List<CityDTO> findAll() {
        log.info("Find all cities");

        List<City> cities = cityRepository.findAll();
        return cityMapper.toDtoList(cities);
    }

    @Override
    @Cacheable("city")
    public CityDTO getOne(Long id) {
        log.info("Find city by id={}", id);
        City city = resolveById(id);
        return cityMapper.toDto(city);
    }

    private City resolveById(Long id) {
        return
                cityRepository.findById(id)
                        .orElseThrow(() -> {
                                    log.error("City with id={} not found!", id);
                                    return new ResourceNotFoundException("City with id=" + id + " not found!");
                                }
                        );
    }

    @Override
    public Boolean existById(Long id) {
        log.info("exist city by id={}", id);
        return cityRepository.existsById(id);
    }


}
