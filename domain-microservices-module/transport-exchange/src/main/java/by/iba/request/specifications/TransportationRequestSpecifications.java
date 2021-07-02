package by.iba.request.specifications;

import by.iba.request.entity.Carrier;
import by.iba.request.entity.Customer;
import by.iba.request.entity.TransportationRequest;
import org.springframework.data.jpa.domain.Specification;

import javax.persistence.criteria.*;

public class TransportationRequestSpecifications {


    private TransportationRequestSpecifications() {
    }


    public static Specification<TransportationRequest> getRequestsByCarrierId(Long id) {

        return (root, criteriaQuery, criteriaBuilder) -> {

            Root<Carrier> carrier = criteriaQuery.from(Carrier.class);

            Join<TransportationRequest, Carrier> carrierJoin = root.join("carrier");

            return criteriaBuilder.equal(criteriaBuilder.lower(carrierJoin.get("id")), id);
        };
    }


    public static Specification<TransportationRequest> getRequestsByCustomerId(Long id) {

        return (root, criteriaQuery, criteriaBuilder) -> {

            Root<Customer> customer = criteriaQuery.from(Customer.class);

            Join<TransportationRequest, Customer> customerJoin = root.join("customer");

            return criteriaBuilder.equal(criteriaBuilder.lower(customerJoin.get("id")), id);
        };
    }

}
