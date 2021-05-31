package by.iba.common.repository;

import by.iba.cargo.domain.Cargo;
import by.iba.common.domain.CargoStowageMethod;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CargoStowageMethodRepository extends JpaRepository<CargoStowageMethod, Long> {
}
