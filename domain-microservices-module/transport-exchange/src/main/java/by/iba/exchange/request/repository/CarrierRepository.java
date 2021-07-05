package by.iba.exchange.request.repository;

import by.iba.exchange.common.repository.BaseAbstractRepository;
import by.iba.exchange.request.entity.Carrier;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface CarrierRepository extends BaseAbstractRepository<Carrier> {

    boolean existsCarrierByUNP(String UNP);

    Optional<Carrier> findCarrierByUNP(String UNP);
}
