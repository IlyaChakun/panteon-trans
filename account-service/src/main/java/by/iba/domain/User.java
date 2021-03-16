package by.iba.domain;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.NotNull;

@Getter
@Setter
@NoArgsConstructor
public class User {

    @NotNull
    @Length(min = 5, max = 20)
    private String email;

    @NotNull
    @Length(min = 6, max = 40)
    private String password;


}
