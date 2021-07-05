package by.iba.exchange.request.repository;

import by.iba.common.repository.BaseAbstractLongKeyRepository;
import by.iba.exchange.request.entity.TransportationRequest;
import org.springframework.stereotype.Repository;

@Repository
public interface TransportationRequestRepository extends BaseAbstractLongKeyRepository<TransportationRequest> {
}
