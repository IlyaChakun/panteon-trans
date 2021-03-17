package by.iba.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class AccountId implements Serializable {

    private Long accountId;

    private Long userId;

    private Long companyId;

}
