package by.iba.exchange.cargo.specifications;

import by.iba.exchange.cargo.domain.CargoOffer;
import org.springframework.data.jpa.domain.Specification;

public class CargoOfferSpecifications {

    public static Specification<CargoOffer> notDeleted() {
        return (root, criteriaQuery, criteriaBuilder) -> criteriaBuilder.isNull(root.get("deletionDate"));
    }

    public static Specification<CargoOffer> getAllCargoByCountryId(Long countryId) {
        return (root, query, criteriaBuilder) -> criteriaBuilder.equal(root.join("loadingLocation").get("countryId"), countryId);
        };
    }
