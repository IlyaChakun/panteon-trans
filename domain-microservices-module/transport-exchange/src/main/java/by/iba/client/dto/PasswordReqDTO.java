package by.iba.client.dto;

import by.iba.common.validation.annotation.EqualPasswordsValidation;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@Getter
@Setter
@EqualPasswordsValidation(message = "passwords are not equal")
public class PasswordReqDTO {

    private String oldPassword;

    private String newPassword;

    private String confirmedPassword;

}
