package by.iba.company.blacklist.dto.mapper;

import by.iba.company.blacklist.domain.Blacklist;
import by.iba.company.blacklist.dto.BlacklistDTO;
import by.iba.common.dto.mapper.AbstractMapperDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class BlacklistMapperDTO extends AbstractMapperDTO<Blacklist, BlacklistDTO> {

    @Autowired
    public BlacklistMapperDTO() {
        super(Blacklist.class, BlacklistDTO.class);
    }

}
