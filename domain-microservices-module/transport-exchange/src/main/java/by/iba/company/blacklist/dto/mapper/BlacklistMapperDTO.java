package by.iba.company.blacklist.dto.mapper;

import by.iba.company.blacklist.domain.Blacklist;
import by.iba.company.blacklist.dto.BlacklistResp;
import by.iba.common.mapper.core.SimpleAbstractMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class BlacklistMapperDTO extends SimpleAbstractMapper<Blacklist, BlacklistResp> {

    @Autowired
    public BlacklistMapperDTO() {
        super(Blacklist.class, BlacklistResp.class);
    }

}
