package by.iba.dto;

import by.iba.common.dto.AbstractDTO;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Null;
import java.util.HashSet;
import java.util.Set;

@Getter
@Setter
@NoArgsConstructor
public class UserDTO extends AbstractDTO {

    @Null(message = "User id must not be set")
    private Long userId;

    @NotNull(message = "Email is required")
    private String email;

    private String password;

    @NotBlank(message = "First name required and cannot be spaces")
    private String firstName;

    @NotBlank(message = "Last name required and cannot be spaces")
    private String lastName;

    @Valid
    private Set<RoleDTO> roles = new HashSet<>();

}
