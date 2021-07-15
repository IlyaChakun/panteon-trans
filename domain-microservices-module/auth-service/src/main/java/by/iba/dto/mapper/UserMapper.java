package by.iba.dto.mapper;

import by.iba.common.mapper.core.FullAbstractMapper;
import by.iba.common.mapper.core.SimpleAbstractMapper;
import by.iba.domain.User;
import by.iba.dto.UserReq;
import by.iba.dto.UserResp;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;

@Component
public class UserMapper extends FullAbstractMapper<User, UserResp, UserReq> {

    @Autowired
    public UserMapper() {
        super(User.class, UserResp.class);
    }

    @PostConstruct
    public void setupMapper() {

        mapper.createTypeMap(User.class, UserResp.class)
                .setPostConverter(toDtoConverter());
        mapper.createTypeMap(UserResp.class, User.class)
                .setPostConverter(toEntityConverter());

        mapper.createTypeMap(UserReq.class, User.class)
                .setPostConverter(toEntityFromReqConverter());
    }
}