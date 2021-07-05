package by.iba.company.companies.domain;

import by.iba.common.domain.BaseCompositeKey;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Getter
@Setter
@NoArgsConstructor
@Embeddable
public class CompositeCompanyId extends BaseCompositeKey {

    private Long companyId;

    private String UNP;

    private Long ownerId;
//    @Id
//    @GeneratedValue(strategy = GenerationType.TABLE)
//    @Column(name = "company_id")
//    private Long companyId;
//
//    @Id
//    @Column(name = "unp", nullable = false, unique = true)
//    private String UNP;
//
//    @Id
//    @Column(name = "owner_id", nullable = false)
//    private Long ownerId;
}
