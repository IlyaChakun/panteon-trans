package by.iba.service;


import by.iba.common.exception.ResourceNotFoundException;
import by.iba.dto.CityDTO;
import by.iba.dto.mapper.CityMapperDTO;
import by.iba.entity.City;
import by.iba.repository.CityRepository;
import lombok.AllArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class CityServiceImpl implements CityService {

    private static final Logger log = LoggerFactory.getLogger(CityServiceImpl.class);

    private final CityRepository cityRepository;
    private final CityMapperDTO cityMapper;

    @Override
    @Cacheable("cities")
    public List<CityDTO> findAll() {
        List<City> cities = cityRepository.findAll();
        return cityMapper.toDtoList(cities);
    }

    @Override
    @Cacheable("city")
    public CityDTO getOne(Long id) {
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
        return cityRepository.existsById(id);
    }


}
