package by.iba.exchange.common.dto.mapper;

import by.iba.exchange.common.domain.TruckBodyType;
import by.iba.exchange.common.dto.TruckBodyTypeDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class TruckBodyTypeMapperDTO extends AbstractMapperDTO<TruckBodyType, TruckBodyTypeDTO> {

    @Autowired
    public TruckBodyTypeMapperDTO() {
        super(TruckBodyType.class, TruckBodyTypeDTO.class);
    }
}