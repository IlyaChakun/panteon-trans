package by.iba.cargo.dto.mapper;

import by.iba.cargo.domain.CargoOffer;
import by.iba.cargo.dto.CargoOfferDTO;
import by.iba.common.dto.mapper.AbstractMapperDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class CargoMapperDTO extends AbstractMapperDTO<CargoOffer, CargoOfferDTO> {

    @Autowired
    public CargoMapperDTO() {
        super(CargoOffer.class, CargoOfferDTO.class);
    }
}
