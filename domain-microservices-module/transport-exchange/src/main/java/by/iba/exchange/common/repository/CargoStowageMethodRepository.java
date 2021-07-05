package by.iba.exchange.common.repository;

import by.iba.exchange.common.domain.CargoStowageMethod;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CargoStowageMethodRepository extends JpaRepository<CargoStowageMethod, Long> {
}
