package by.iba.dto;

import by.iba.common.dto.core.BaseAbstractReq;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;

@Getter
@Setter
@NoArgsConstructor
public class AccountReq extends BaseAbstractReq {

    @Valid
    @NotNull
    private UserReq user;

}
