package by.iba.dto;

import by.iba.common.dto.AbstractDTO;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.Valid;
import javax.validation.constraints.Null;

@Getter
@Setter
@NoArgsConstructor
public class AccountDTO extends AbstractDTO {

    @Null(message = "Account ID could not be set")
    private Long accountId;

    private CompanyDTO company;

    @Valid
    private UserDTO user;

}
