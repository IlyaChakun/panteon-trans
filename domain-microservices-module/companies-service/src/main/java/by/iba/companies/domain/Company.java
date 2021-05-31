package by.iba.companies.domain;

import by.iba.common.domain.AbstractEntity;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Entity
@IdClass(CompanyId.class)
@Table(name = "companies")
@Getter
@Setter
@NoArgsConstructor
public class Company extends AbstractEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.TABLE)
    @Column(name = "company_id")
    private Long companyId;

    @Id
    @Column(name = "unp", nullable = false, unique = true)
    private String UNP;

    @Id
    @Column(name = "owner_id", nullable = false)
    private Long ownerId;

    @Column(name = "email", nullable = false, unique = true)
    private String email;

    @Column(name = "site", unique = true)
    private String site;

    @Column(name = "title", nullable = false)
    private String title;

    @Column(name = "description")
    private String description;

    @Column(name = "country_id", nullable = false)
    private Long countryId;

    @Column(name = "address", nullable = false)
    private String address;

    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private List<PhoneNumber> phoneNumbers = new ArrayList<>();

    @Column(name = "foundation_date", nullable = false)
    private LocalDate foundationDate;

    @Column(name = "business_type")
    @Enumerated
    private BusinessType businessType;

}
