package by.iba.workers.domain;

import by.iba.common.domain.AbstractEntity;
import by.iba.companies.domain.CompanyId;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Table(name = "companies_workers")
@Getter
@Setter
@NoArgsConstructor
public class CompanyWorker  extends AbstractEntity {

    @Id
    @Column(name = "user_id")
    private Long userId;

    private Long CompanyPositionId;

}
