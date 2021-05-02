package by.iba.blacklist.domain;

import by.iba.companies.domain.CompanyId;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "companies_black_list")
@Getter
@Setter
@NoArgsConstructor
public class CompanyBlackListHistory {

    @Id
    @GeneratedValue(strategy = GenerationType.TABLE)
    @Column(name = "id")
    private Long id;

    private Long companyId;

    @OneToMany
    private List<CompanyBlackListAccident> companyBlackListHistory = new ArrayList<>();

}
