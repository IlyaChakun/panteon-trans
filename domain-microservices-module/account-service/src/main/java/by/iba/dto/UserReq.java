package by.iba.dto;

import by.iba.client.dto.RoleResp;
import by.iba.common.dto.core.BaseAbstractReq;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.HashSet;
import java.util.Set;

@Getter
@Setter
@NoArgsConstructor
public class UserReq extends BaseAbstractReq {

    @NotNull(message = "Email is required")
    private String email;

    private String password;

    @NotBlank(message = "First name required and cannot be spaces")
    private String firstName;

    @NotBlank(message = "Last name required and cannot be spaces")
    private String lastName;

    @Valid
    private Set<RoleResp> roles = new HashSet<>();

}
