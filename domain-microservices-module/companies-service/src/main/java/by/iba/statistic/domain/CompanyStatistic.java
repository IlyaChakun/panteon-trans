package by.iba.statistic.domain;

import by.iba.common.domain.AbstractEntity;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Table(name = "companies_statistic")
@Getter
@Setter
@NoArgsConstructor
public class CompanyStatistic extends AbstractEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.TABLE)
    @Column(name = "id")
    private Long id;


    private Long companyId;

    private Long workersCount;

    private Long offersCount;


}
