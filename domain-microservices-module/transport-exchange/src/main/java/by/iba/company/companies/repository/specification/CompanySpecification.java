package by.iba.company.companies.repository.specification;

import by.iba.company.companies.domain.Company;
import org.springframework.data.jpa.domain.Specification;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.Expression;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Set;

public final class CompanySpecification {

    private CompanySpecification() {
    }

    public static Specification<Company> findByFeatureIds(Set<Long> companyFeatureIds) {
        return (root, query, cb) -> {
            query.distinct(true);
            List<Predicate> predicates = buildPredicates(root, cb, companyFeatureIds);
            return
                    cb.or(predicates.toArray(new Predicate[0]));
        };
    }

    private static List<Predicate> buildPredicates(Root<Company> root,
                                                   CriteriaBuilder cb,
                                                   Set<Long> companyFeatureIds) {
        List<Predicate> predicates = new ArrayList<>();
        Expression<Collection<Company>> companyFeatures = root.get("companyFeatures");
        companyFeatureIds.forEach(id ->
                predicates.add(//todo mistake here
                        cb.and(cb.equal(root.get("id"), id), cb.isMember(root, companyFeatures))
                )
        );
        return predicates;
    }
}
