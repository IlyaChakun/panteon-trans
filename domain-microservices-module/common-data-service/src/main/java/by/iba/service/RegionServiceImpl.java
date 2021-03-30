package by.iba.service;


import by.iba.common.exception.ResourceNotFoundException;
import by.iba.dto.RegionDTO;
import by.iba.dto.mapper.RegionMapperDTO;
import by.iba.entity.Region;
import by.iba.repository.RegionRepository;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
@Slf4j
public class RegionServiceImpl implements RegionService {

    private final RegionRepository regionRepository;
    private final RegionMapperDTO regionMapper;


    @Override
    @Cacheable("regions")
    public List<RegionDTO> findAll() {
        log.info("find all regions");

        List<Region> regions = regionRepository.findAll();
        return regionMapper.toDtoList(regions);
    }

    @Override
    @Cacheable("region")
    public RegionDTO getOne(Long id) {
        log.info("get one region by id={}", id);

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
        log.info("exist region by id={}", id);

        return regionRepository.existsById(id);
    }


}
