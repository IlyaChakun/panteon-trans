package by.iba.exchange.truck.dto.mapper;

import by.iba.common.mapper.core.SimpleAbstractMapper;
import by.iba.exchange.truck.domain.TruckOffer;
import by.iba.exchange.truck.dto.TruckOfferResp;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class TruckOfferMapperDTO extends SimpleAbstractMapper<TruckOffer, TruckOfferResp> {

    @Autowired
    public TruckOfferMapperDTO() {
        super(TruckOffer.class, TruckOfferResp.class);
    }
}
