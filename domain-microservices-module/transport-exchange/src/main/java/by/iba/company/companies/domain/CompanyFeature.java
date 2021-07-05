package by.iba.company.companies.domain;

import by.iba.common.domain.core.BaseAbstractEntity;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "company_features")
@Getter
@Setter
@NoArgsConstructor
public class CompanyFeature extends BaseAbstractEntity {

    @Column(name = "title")
    private String title;


}
