package by.iba.dto;

import by.iba.common.dto.core.BaseAbstractReq;
import by.iba.domain.Status;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class AccountReq extends BaseAbstractReq {

    private Long accountId;

    private CompanyReq company;

    private UserReq user;

    private Status status;

}
