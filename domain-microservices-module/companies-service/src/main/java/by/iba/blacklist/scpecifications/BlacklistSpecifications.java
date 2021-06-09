package by.iba.blacklist.scpecifications;

import by.iba.blacklist.domain.Blacklist;
import org.springframework.data.jpa.domain.Specification;

public class BlacklistSpecifications {

    public static Specification<Blacklist> notDeleted() {
        return (root, criteriaQuery, criteriaBuilder) -> criteriaBuilder
                .equal(root.get("dateOfLastUpdate"),
                        root.get("dateOfCreation"));
    }
}
