package by.iba.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
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

    private String fistName;

    private String lastName;

    private Set<RoleDTO> roles = new HashSet<>();

}
