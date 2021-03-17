package by.iba.domain;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.UUID;

@Entity
@Table(name = "confirmation_tokens")
@Getter
@Setter
@NoArgsConstructor
public class ConfirmationToken extends AbstractEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false, unique = true)
    private Long id;

    @Column(name = "confirmation_token")
    private String confirmationToken;

    @Column(name = "user_id")
    private Long userId;

    public ConfirmationToken(Long userId) {
        this.userId = userId;
        confirmationToken = UUID.randomUUID().toString();
    }

}
