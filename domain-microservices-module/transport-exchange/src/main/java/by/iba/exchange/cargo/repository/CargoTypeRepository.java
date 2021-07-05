package by.iba.exchange.cargo.repository;

import by.iba.common.repository.BaseAbstractLongKeyRepository;
import by.iba.exchange.cargo.domain.CargoType;
import org.springframework.stereotype.Repository;

@Repository
public interface CargoTypeRepository extends BaseAbstractLongKeyRepository<CargoType> {
}
