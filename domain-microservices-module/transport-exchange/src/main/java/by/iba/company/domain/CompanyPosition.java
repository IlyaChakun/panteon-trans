package by.iba.company.domain;

import by.iba.common.domain.BaseAbstractEntity;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "company_positions")
@Getter
@Setter
@NoArgsConstructor
public class CompanyPosition extends BaseAbstractEntity {

    private String positionName;

}
