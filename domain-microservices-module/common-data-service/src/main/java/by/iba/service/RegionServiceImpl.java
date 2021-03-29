package by.iba.service;


import by.iba.common.exception.ResourceNotFoundException;
import by.iba.dto.RegionDTO;
import by.iba.dto.mapper.RegionMapperDTO;
import by.iba.entity.Region;
import by.iba.repository.RegionRepository;
import lombok.AllArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class RegionServiceImpl implements RegionService {

    private static final Logger log = LoggerFactory.getLogger(RegionServiceImpl.class);

    private final RegionRepository regionRepository;
    private final RegionMapperDTO regionMapper;


    @Override
    @Cacheable("regions")
    public List<RegionDTO> findAll() {
        List<Region> regions = regionRepository.findAll();
        return regionMapper.toDtoList(regions);
    }

    @Override
    @Cacheable("region")
    public RegionDTO getOne(Long id) {
        Region region = resolveById(id);
        return regionMapper.toDto(region);
    }

    private Region resolveById(Long id) {
        return
                regionRepository.findById(id)
                        .orElseThrow(() -> {
                                    log.error("Region with id={} not found!", id);
                                    return new ResourceNotFoundException("Region with id=" + id + " not found!");
                                }
                        );
    }

    @Override
    public Boolean existById(Long id) {
        return regionRepository.existsById(id);
    }


}
