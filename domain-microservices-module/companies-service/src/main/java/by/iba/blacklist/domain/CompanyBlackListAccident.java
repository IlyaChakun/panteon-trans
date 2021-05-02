package by.iba.blacklist.domain;

import by.iba.common.domain.AbstractEntity;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Table(name = "companies_black_list_accidents")
@Getter
@Setter
@NoArgsConstructor
public class CompanyBlackListAccident extends AbstractEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.TABLE)
    @Column(name = "id")
    private Long id;

    private Long companyId;

    private String description;

    private String reason;

    private Boolean isActive;

}
