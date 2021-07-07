package by.iba.domain;

import by.iba.common.domain.core.BaseEntity;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import javax.persistence.*;

@Entity
@IdClass(AccountId.class)
@Table(name = "user_accounts")
@Getter
@Setter
@NoArgsConstructor
public class Account extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.TABLE)
    @Column(name = "account_id", nullable = false, unique = true)
    private Long accountId;

    @Id
    @Column(name = "user_id", nullable = false, unique = true)
    private Long userId;

    @Column(name = "company_id")
    private Long companyId;

    @Column(name = "status")
    @Enumerated
    private Status status = Status.PENDING;
}
