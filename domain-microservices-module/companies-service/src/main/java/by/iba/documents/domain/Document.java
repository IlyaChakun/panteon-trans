package by.iba.documents.domain;

import by.iba.common.domain.AbstractEntity;
import by.iba.companies.domain.CompanyId;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Table(name = "companies_documents")
@Getter
@Setter
@NoArgsConstructor
public class Document extends AbstractEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.TABLE)
    @Column(name = "id")
    private Long id;

    private Long companyId;

    private Byte[] file;

}
