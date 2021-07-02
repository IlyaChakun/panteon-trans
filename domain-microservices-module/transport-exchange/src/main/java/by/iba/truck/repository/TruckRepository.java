package by.iba.truck.repository;

import by.iba.common.repository.BaseAbstractRepository;
import by.iba.truck.domain.TruckOffer;
import org.springframework.stereotype.Repository;

@Repository
public interface TruckRepository extends BaseAbstractRepository<TruckOffer> {
}
