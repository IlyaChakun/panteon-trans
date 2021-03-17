package by.iba.domain;

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
public class Account extends AbstractEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.TABLE)
    @Column(name = "account_id", nullable = false, unique = true)
    private Long accountId;

    @Id
    private Long userId;

    @Id
    private Long companyId;

}
