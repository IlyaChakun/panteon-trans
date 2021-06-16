package by.iba.cargo.specifications;

import by.iba.cargo.domain.Cargo;

import by.iba.common.domain.LoadingLocation;
import org.springframework.data.jpa.domain.Specification;

import javax.persistence.criteria.Join;
import javax.persistence.criteria.Predicate;
import java.util.List;

public class CargoSpecifications {

    public static Specification<Cargo> notDeleted() {
        return (root, criteriaQuery, criteriaBuilder) -> criteriaBuilder.isNull(root.get("deletionDate"));
    }

    public static Specification<Cargo> getAllCargoByCountryId(Long countryId) {
        return (root, query, criteriaBuilder) -> criteriaBuilder.equal(root.join("loadingLocation").get("countryId"), countryId);
        };
    }
