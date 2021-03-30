package by.iba.cargo.dto.mapper;

import by.iba.cargo.domain.Cargo;
import by.iba.cargo.dto.CargoDTO;
import by.iba.common.dto.mapper.AbstractMapperDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class CargoMapperDTO extends AbstractMapperDTO<Cargo, CargoDTO> {

    @Autowired
    public CargoMapperDTO() {
        super(Cargo.class, CargoDTO.class);
    }
}
