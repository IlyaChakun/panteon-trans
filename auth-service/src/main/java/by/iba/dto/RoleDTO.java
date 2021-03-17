package by.iba.dto;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.NotNull;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class RoleDTO {

    @NotNull(message = "Role name is required!")
    private String roleName;

    private String displayName;

    private Boolean isDisplayed;

}
