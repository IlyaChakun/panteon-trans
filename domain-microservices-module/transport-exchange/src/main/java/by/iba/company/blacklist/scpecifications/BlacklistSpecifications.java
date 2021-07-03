package by.iba.company.blacklist.scpecifications;

import by.iba.company.blacklist.domain.Blacklist;
import org.springframework.data.jpa.domain.Specification;

public class BlacklistSpecifications {

    private BlacklistSpecifications(){}

    public static Specification<Blacklist> notDeleted() {
        return (root, criteriaQuery, criteriaBuilder) -> criteriaBuilder
                .isNull(root.get("deletionDate"));
    }
}
