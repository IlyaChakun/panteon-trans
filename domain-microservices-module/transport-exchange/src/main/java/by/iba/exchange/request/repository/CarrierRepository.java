package by.iba.exchange.request.repository;

import by.iba.common.repository.BaseAbstractLongKeyRepository;
import by.iba.exchange.request.entity.Carrier;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface CarrierRepository extends BaseAbstractLongKeyRepository<Carrier> {

    boolean existsCarrierByUNP(String UNP);

    Optional<Carrier> findCarrierByUNP(String UNP);
}
