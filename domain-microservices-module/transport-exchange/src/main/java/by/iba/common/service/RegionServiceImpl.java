package by.iba.common.service;


import by.iba.common.dto.RegionResp;
import by.iba.common.dto.mapper.RegionMapper;
import by.iba.common.exception.ResourceNotFoundException;
import by.iba.common.repository.RegionRepository;
import by.iba.common.entity.Region;
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
    private final RegionMapper regionMapper;


    @Override
    @Cacheable("regions")
    public List<RegionResp> findAll() {
        log.info("find all regions");

        List<Region> regions = regionRepository.findAll();
        return regionMapper.toDtoList(regions);
    }

    @Override
    @Cacheable("region")
    public RegionResp getOne(Long id) {
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
