package by.iba.workers.domain;

import by.iba.common.domain.BaseAbstractEntity;
import by.iba.companies.domain.CompanyId;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.IdClass;
import javax.persistence.Table;

@Entity
@Table(name = "company_positions")
@Getter
@Setter
@NoArgsConstructor
public class CompanyPosition extends BaseAbstractEntity {

    private String positionName;

}
