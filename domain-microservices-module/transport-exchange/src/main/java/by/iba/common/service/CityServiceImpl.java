package by.iba.common.service;


import by.iba.common.dto.CityResp;
import by.iba.common.dto.mapper.CityMapper;
import by.iba.common.exception.ResourceNotFoundException;
import by.iba.common.repository.CityRepository;
import by.iba.common.entity.City;
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
    private final CityMapper cityMapper;

    @Override
    @Cacheable("cities")
    public List<CityResp> findAll() {
        

        List<City> cities = cityRepository.findAll();
        return cityMapper.toDtoList(cities);
    }

    @Override
    @Cacheable("city")
    public CityResp getOne(Long id) {
        
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
