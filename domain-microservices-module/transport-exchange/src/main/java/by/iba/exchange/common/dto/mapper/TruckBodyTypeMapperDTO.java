package by.iba.exchange.common.dto.mapper;

import by.iba.common.mapper.core.SimpleAbstractMapper;
import by.iba.exchange.common.domain.TruckBodyType;
import by.iba.exchange.common.dto.TruckBodyTypeResp;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class TruckBodyTypeMapperDTO extends SimpleAbstractMapper<TruckBodyType, TruckBodyTypeResp> {

    @Autowired
    public TruckBodyTypeMapperDTO() {
        super(TruckBodyType.class, TruckBodyTypeResp.class);
    }
}