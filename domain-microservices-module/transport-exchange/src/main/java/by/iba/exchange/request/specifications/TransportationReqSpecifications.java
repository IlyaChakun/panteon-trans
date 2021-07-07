package by.iba.exchange.request.specifications;

import by.iba.exchange.request.entity.Carrier;
import by.iba.exchange.request.entity.Customer;
import by.iba.exchange.request.entity.TransportationReq;
import org.springframework.data.jpa.domain.Specification;

import javax.persistence.criteria.*;

public class TransportationReqSpecifications {


    private TransportationReqSpecifications() {
    }


    public static Specification<TransportationReq> getRequestsByCarrierId(Long id) {

        return (root, criteriaQuery, criteriaBuilder) -> {

            Root<Carrier> carrier = criteriaQuery.from(Carrier.class);

            Join<TransportationReq, Carrier> carrierJoin = root.join("carrier");

            return criteriaBuilder.equal(criteriaBuilder.lower(carrierJoin.get("id")), id);
        };
    }


    public static Specification<TransportationReq> getRequestsByCustomerId(Long id) {

        return (root, criteriaQuery, criteriaBuilder) -> {

            Root<Customer> customer = criteriaQuery.from(Customer.class);

            Join<TransportationReq, Customer> customerJoin = root.join("customer");

            return criteriaBuilder.equal(criteriaBuilder.lower(customerJoin.get("id")), id);
        };
    }

}
