package by.iba.cargo.dto.mapper;

import by.iba.cargo.domain.CargoType;
import by.iba.cargo.dto.CargoTypeDTO;
import by.iba.common.dto.mapper.AbstractMapperDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class CargoTypeMapperDTO extends AbstractMapperDTO<CargoType, CargoTypeDTO> {

    @Autowired
    public CargoTypeMapperDTO() {
        super(CargoType.class, CargoTypeDTO.class);
    }
}

