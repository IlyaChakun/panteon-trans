package by.iba.client.dto;


import by.iba.common.dto.core.FullAbstractResp;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.NotNull;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class RoleResp extends FullAbstractResp {

    @NotNull(message = "Role name is required!")
    private String roleName;

    private String displayName;

    private Boolean isDisplayed;

}
