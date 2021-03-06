package by.iba.exchange.cargo.dto.mapper;

import by.iba.exchange.cargo.domain.CargoType;
import by.iba.exchange.cargo.dto.CargoTypeResp;
import by.iba.common.mapper.core.SimpleAbstractMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class CargoTypeMapperDTO extends SimpleAbstractMapper<CargoType, CargoTypeResp> {

    @Autowired
    public CargoTypeMapperDTO() {
        super(CargoType.class, CargoTypeResp.class);
    }
}

