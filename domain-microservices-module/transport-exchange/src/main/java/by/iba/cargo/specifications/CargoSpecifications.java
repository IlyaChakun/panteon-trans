package by.iba.cargo.specifications;

import by.iba.cargo.domain.Cargo;
import org.springframework.data.jpa.domain.Specification;

public class CargoSpecifications {

    public static Specification<Cargo> notDeleted() {
        return (root, criteriaQuery, criteriaBuilder) -> criteriaBuilder.isNull(root.get("deletionDate"));
    }
}
