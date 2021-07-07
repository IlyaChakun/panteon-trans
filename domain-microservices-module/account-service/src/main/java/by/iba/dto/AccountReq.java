package by.iba.dto;

import by.iba.common.dto.core.BaseAbstractReq;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class AccountReq extends BaseAbstractReq {

    private UserReq user;

}
