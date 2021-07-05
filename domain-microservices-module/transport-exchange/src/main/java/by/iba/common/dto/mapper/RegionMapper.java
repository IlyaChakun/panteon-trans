package by.iba.common.dto.mapper;

import by.iba.common.dto.RegionResp;
import by.iba.common.entity.Region;
import by.iba.common.mapper.core.SimpleAbstractMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class RegionMapper extends SimpleAbstractMapper<Region, RegionResp> {

    @Autowired
    public RegionMapper() {
        super(Region.class, RegionResp.class);
    }
}