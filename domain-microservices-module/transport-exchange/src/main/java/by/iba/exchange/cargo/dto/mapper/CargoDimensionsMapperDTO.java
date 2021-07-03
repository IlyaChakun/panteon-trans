package by.iba.exchange.cargo.dto.mapper;

import by.iba.exchange.cargo.domain.CargoDimensions;
import by.iba.exchange.cargo.dto.CargoDimensionsDTO;
import by.iba.common.dto.mapper.AbstractMapperDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class CargoDimensionsMapperDTO extends AbstractMapperDTO<CargoDimensions, CargoDimensionsDTO> {

    @Autowired
    public CargoDimensionsMapperDTO() {
        super(CargoDimensions.class, CargoDimensionsDTO.class);
    }

}
