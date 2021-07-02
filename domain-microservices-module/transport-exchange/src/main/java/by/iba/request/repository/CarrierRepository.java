package by.iba.request.repository;

import by.iba.common.repository.BaseAbstractRepository;
import by.iba.request.entity.Carrier;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface CarrierRepository extends BaseAbstractRepository<Carrier> {

    boolean existsCarrierByUNP(String UNP);

    Optional<Carrier> findCarrierByUNP(String UNP);
}
