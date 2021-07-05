package by.iba.exchange.request.dto.mapper;

import by.iba.exchange.common.dto.mapper.AbstractMapperDTO;
import by.iba.exchange.request.dto.TransportationRequestDTO;
import by.iba.exchange.request.entity.TransportationRequest;
import org.springframework.stereotype.Component;

@Component
public class TransportationRequestMapperDTO extends AbstractMapperDTO<TransportationRequest, TransportationRequestDTO> {


    public TransportationRequestMapperDTO() {
        super(TransportationRequest.class, TransportationRequestDTO.class);
    }

}
