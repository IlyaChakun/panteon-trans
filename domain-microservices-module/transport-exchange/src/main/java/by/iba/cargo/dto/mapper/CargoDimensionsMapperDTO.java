package by.iba.cargo.dto.mapper;

import by.iba.cargo.domain.CargoDimensions;
import by.iba.cargo.dto.CargoDimensionsDTO;
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
