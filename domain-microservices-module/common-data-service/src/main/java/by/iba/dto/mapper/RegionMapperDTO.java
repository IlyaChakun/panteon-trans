package by.iba.dto.mapper;

import by.iba.common.dto.mapper.AbstractMapperDTO;
import by.iba.dto.RegionDTO;
import by.iba.entity.Region;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class RegionMapperDTO extends AbstractMapperDTO<Region, RegionDTO> {

    @Autowired
    public RegionMapperDTO() {
        super(Region.class, RegionDTO.class);
    }
}