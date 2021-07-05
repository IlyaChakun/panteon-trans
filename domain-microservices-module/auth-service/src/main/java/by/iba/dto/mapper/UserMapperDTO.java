package by.iba.dto.mapper;

import by.iba.common.mapper.core.SimpleAbstractMapper;
import by.iba.domain.User;
import by.iba.dto.UserResp;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class UserMapperDTO extends SimpleAbstractMapper<User, UserResp> {

    @Autowired
    public UserMapperDTO() {
        super(User.class, UserResp.class);
    }
}