package by.iba.client.dto;

import by.iba.common.dto.core.BaseAbstractResp;
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
public class UserResp extends BaseAbstractResp {

    private Long userId;

    private String email;

    private String password;

    private String firstName;

    private String lastName;

    @Valid
    private Set<RoleResp> roles = new HashSet<>();

}
