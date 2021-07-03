package by.iba.exchange.cargo.repository;

import by.iba.exchange.cargo.domain.CargoType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CargoTypeRepository extends JpaRepository<CargoType, Long> {
}
