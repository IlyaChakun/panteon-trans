package by.iba.dto;

import by.iba.common.dto.core.BaseAbstractReq;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Getter
@Setter
@NoArgsConstructor
public class UserReq extends BaseAbstractReq {

    @NotNull(message = "Email is required")
    private String email;

    @NotBlank(message = "Password cannot be empty")
    private String password;

    @NotBlank(message = "Confirmed password cannot be empty")
    private String confirmedPassword;
/*
    @NotBlank(message = "First name required and cannot be spaces")
    private String firstName;

    @NotBlank(message = "Last name required and cannot be spaces")
    private String lastName;*/

}
