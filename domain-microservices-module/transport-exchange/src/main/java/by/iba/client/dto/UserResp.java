package by.iba.client.dto;

import by.iba.common.dto.core.BaseAbstractResp;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.Null;

@Getter
@Setter
@NoArgsConstructor
public class UserResp extends BaseAbstractResp {

    @Null(message = "User id could not be set")
    private Long userId;

    private String email;

    private String password;

    private String firstName;

    private String lastName;

}
