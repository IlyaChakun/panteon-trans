package by.iba.dto;

import by.iba.common.dto.core.BaseAbstractResp;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.Valid;
import javax.validation.constraints.Null;

@Getter
@Setter
@NoArgsConstructor
public class AccountResp extends BaseAbstractResp {

    @Null(message = "Account ID could not be set")
    private Long accountId;

    private CompanyResp company;

    @Valid
    private UserResp user;

}
