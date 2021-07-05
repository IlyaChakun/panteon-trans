package by.iba.domain;

import by.iba.common.domain.BaseCompositeKey;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class AccountId extends BaseCompositeKey {

    private Long accountId;

    private Long userId;

}
