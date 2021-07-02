package by.iba.request.dto.mapper;

import by.iba.common.dto.mapper.AbstractMapperDTO;
import by.iba.request.dto.TransportationRequestDTO;
import by.iba.request.entity.TransportationRequest;
import org.springframework.stereotype.Component;

@Component
public class TransportationRequestMapperDTO extends AbstractMapperDTO<TransportationRequest, TransportationRequestDTO> {


    public TransportationRequestMapperDTO() {
        super(TransportationRequest.class, TransportationRequestDTO.class);
    }

}
