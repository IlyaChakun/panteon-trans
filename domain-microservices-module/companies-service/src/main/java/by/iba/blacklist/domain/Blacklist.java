package by.iba.blacklist.domain;

import by.iba.common.domain.AbstractEntity;
import by.iba.common.domain.BaseEntity;
import by.iba.companies.domain.CompanyId;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.SQLDelete;

import javax.persistence.*;
import java.time.LocalDate;

@EqualsAndHashCode(callSuper = true)
@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@IdClass(CompanyId.class)
@Table(name = "blacklist")
@SQLDelete(sql = "UPDATE blacklist SET date_of_last_update = GETDATE() WHERE id=?")
public class Blacklist extends AbstractEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.TABLE)
    @Column(name = "id")
    private Long id;

    @Id
    @Column(name = "company_id")
    private Long companyId;

    @Column(name = "reason")
    private String reason;

}