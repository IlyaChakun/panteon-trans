package by.iba.client.dto;

import by.iba.common.dto.core.FullAbstractResp;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.Valid;
import java.util.HashSet;
import java.util.Set;

@Getter
@Setter
@NoArgsConstructor
public class UserResp extends FullAbstractResp {

    private Long userId;

    private String email;

    private String password;

    private String firstName;

    private String lastName;

    @Valid
    private Set<RoleResp> roles = new HashSet<>();

}
