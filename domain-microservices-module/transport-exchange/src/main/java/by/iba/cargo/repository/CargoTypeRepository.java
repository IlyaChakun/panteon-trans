package by.iba.cargo.repository;

import by.iba.cargo.domain.CargoType;
import by.iba.common.repository.BaseAbstractRepository;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CargoTypeRepository extends BaseAbstractRepository<CargoType> {
}
