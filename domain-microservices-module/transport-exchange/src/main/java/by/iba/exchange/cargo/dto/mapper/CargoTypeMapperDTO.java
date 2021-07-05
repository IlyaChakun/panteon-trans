package by.iba.exchange.cargo.dto.mapper;

import by.iba.exchange.cargo.domain.CargoType;
import by.iba.exchange.cargo.dto.CargoTypeDTO;
import by.iba.exchange.common.dto.mapper.AbstractMapperDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class CargoTypeMapperDTO extends AbstractMapperDTO<CargoType, CargoTypeDTO> {

    @Autowired
    public CargoTypeMapperDTO() {
        super(CargoType.class, CargoTypeDTO.class);
    }
}

