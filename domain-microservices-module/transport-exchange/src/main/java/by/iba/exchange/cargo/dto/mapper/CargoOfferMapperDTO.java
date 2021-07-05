package by.iba.exchange.cargo.dto.mapper;

import by.iba.exchange.cargo.domain.CargoOffer;
import by.iba.exchange.cargo.dto.CargoOfferResp;
import by.iba.common.mapper.core.SimpleAbstractMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class CargoOfferMapperDTO extends SimpleAbstractMapper<CargoOffer, CargoOfferResp> {

    @Autowired
    public CargoOfferMapperDTO() {
        super(CargoOffer.class, CargoOfferResp.class);
    }
}
