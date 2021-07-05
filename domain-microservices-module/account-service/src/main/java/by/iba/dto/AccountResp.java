package by.iba.dto;

import by.iba.client.dto.CompanyResp;
import by.iba.client.dto.UserResp;
import by.iba.common.dto.core.BaseAbstractResp;
import by.iba.domain.Status;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class AccountResp extends BaseAbstractResp {

    private Long accountId;

    private CompanyResp company;

    private UserResp user;

    private Status status;

}
