package by.iba.company.domain;

import by.iba.common.domain.BaseEntity;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Table(name = "companies_documents")
@Getter
@Setter
@NoArgsConstructor
public class Document extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.TABLE)
    @Column(name = "id")
    private Long id;

    private Long companyId;

    private Byte[] file;

}
