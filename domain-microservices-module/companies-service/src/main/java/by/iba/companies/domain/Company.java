package by.iba.companies.domain;

import by.iba.common.domain.AbstractEntity;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.Date;

@Entity
@IdClass(CompanyId.class)
@Table(name = "companies")
@Getter
@Setter
@NoArgsConstructor
public class Company extends AbstractEntity {

    @Id
    @Column(name = "unp", nullable = false, unique = true)
    private String UNP; //Payer account number

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

    @Column(name = "country", nullable = false)
    private String country;

    @Column(name = "address", nullable = false)
    private String address; //адрес  + юр адрес

    @Column(name = "phone_number", nullable = false, unique = true)
    private String phoneNumber;

    @Column(name = "foundation_date", nullable = false)
    private Date foundationDate;

    @Column(name = "business_type")
    @Enumerated
    private BusinessType businessType;

}
