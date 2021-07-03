package by.iba.company.domain;

import by.iba.common.domain.BaseEntity;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "companies_workers")
@Getter
@Setter
@NoArgsConstructor
public class CompanyWorker  extends BaseEntity {

    @Id
    @Column(name = "user_id")
    private Long userId;

    private Long CompanyPositionId;

}
