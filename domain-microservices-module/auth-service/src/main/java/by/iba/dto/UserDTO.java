package by.iba.dto;

import by.iba.common.dto.AbstractDTO;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.Null;
import java.util.HashSet;
import java.util.Set;

@Getter
@Setter
@NoArgsConstructor
public class UserDTO extends AbstractDTO {

    @Null(message = "User id could not be set")
    private Long userId;

    private String email;

    private String password;

    private String firstName;

    private String lastName;

    private Set<RoleDTO> roles = new HashSet<>();

}
