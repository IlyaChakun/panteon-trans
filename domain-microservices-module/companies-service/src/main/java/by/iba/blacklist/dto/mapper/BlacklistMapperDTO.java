package by.iba.blacklist.dto.mapper;

import by.iba.blacklist.domain.Blacklist;
import by.iba.blacklist.dto.BlacklistDTO;
import by.iba.common.dto.mapper.AbstractMapperDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;

@Component
public class BlacklistMapperDTO extends AbstractMapperDTO<Blacklist, BlacklistDTO> {

    @Autowired
    public BlacklistMapperDTO() {
        super(Blacklist.class, BlacklistDTO.class);
    }

}
