package by.iba.dto;

import by.iba.common.validation.annotation.EqualPasswordsValidation;
import by.iba.common.validation.annotation.PasswordStrengthValidation;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.*;
import java.util.Set;

@Getter
@Setter
@NoArgsConstructor
@EqualPasswordsValidation(message = "passwords r not equal")
public class PasswordDTO {

    private String oldPassword;

    private String newPassword;

    private String confirmedPassword;

}
