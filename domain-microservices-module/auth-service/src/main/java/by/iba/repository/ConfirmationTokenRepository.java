package by.iba.repository;

import by.iba.domain.ConfirmationToken;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import java.util.Optional;

public interface ConfirmationTokenRepository extends JpaRepository<ConfirmationToken, Long>, JpaSpecificationExecutor<ConfirmationToken> {

    Optional<ConfirmationToken> findByConfirmationToken(final String confirmationToken);

    ConfirmationToken findByUserId(Long id);

}
