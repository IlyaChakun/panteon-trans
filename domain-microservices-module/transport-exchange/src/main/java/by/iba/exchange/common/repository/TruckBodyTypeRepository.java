package by.iba.exchange.common.repository;

import by.iba.exchange.common.domain.TruckBodyType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TruckBodyTypeRepository extends JpaRepository<TruckBodyType, Long> {
}
