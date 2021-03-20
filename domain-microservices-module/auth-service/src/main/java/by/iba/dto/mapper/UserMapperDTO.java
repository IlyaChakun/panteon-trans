package by.iba.dto.mapper;

import by.iba.common.dto.mapper.AbstractMapperDTO;
import by.iba.domain.User;
import by.iba.dto.UserDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class UserMapperDTO extends AbstractMapperDTO<User, UserDTO> {

    @Autowired
    public UserMapperDTO() {
        super(User.class, UserDTO.class);
    }
}