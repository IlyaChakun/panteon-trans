package by.iba.company.companies.domain;

import by.iba.common.domain.Address;
import by.iba.common.domain.core.BaseEntity;
import by.iba.common.validation.annotation.ValidPhones;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Entity
@Table(name = "companies")
@Getter
@Setter
@NoArgsConstructor
public class Company extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.TABLE)
    @Column(name = "company_id")
    private Long companyId;

    @Column(name = "unp", nullable = false, unique = true)
    private String UNP;

    @Column(name = "owner_id", nullable = false, unique = true)
    private Long ownerId;

    @Column(name = "email", nullable = false, unique = true)
    private String email;

    @Column(name = "site", unique = true)
    private String site;

    @Column(name = "title", nullable = false)
    private String title;

    @Column(name = "description")
    private String description;

    private Address address;

    @NotEmpty(message = "validation.company.phone_numbers_not_present")
    @ValidPhones
    @ElementCollection
    @Column(name = "phone_numbers")
    private List<String> phoneNumbers = new ArrayList<>();

    @Column(name = "foundation_date", nullable = false)
    private LocalDate foundationDate;

    @Column(name = "business_type")
    @Enumerated
    private BusinessType businessType;

    @Column(name = "status")
    @Enumerated
    private Status status = Status.PENDING;

    @ManyToMany
    @JoinTable(name = "company_active_features",
            joinColumns = {
                    @JoinColumn(
                            name = "feature_company_id",
                            referencedColumnName = "company_id"),
            },
            inverseJoinColumns = {
                    @JoinColumn(name = "feature_id",
                            referencedColumnName = "id")
            })
    private Set<CompanyFeature> companyFeatures = new HashSet<>();

}
