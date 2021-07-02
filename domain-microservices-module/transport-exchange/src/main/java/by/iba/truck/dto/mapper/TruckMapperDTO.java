package by.iba.truck.dto.mapper;

import by.iba.common.dto.mapper.AbstractMapperDTO;
import by.iba.truck.domain.TruckOffer;
import by.iba.truck.dto.TruckOfferDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class TruckMapperDTO extends AbstractMapperDTO<TruckOffer, TruckOfferDTO> {

    @Autowired
    public TruckMapperDTO() {
        super(TruckOffer.class, TruckOfferDTO.class);
    }
}
