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
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

@EqualsAndHashCode()
@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "blacklist")
public class Blacklist extends AbstractEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.TABLE)
    @Column(name = "id")
    @EqualsAndHashCode.Include
    private Long id;

    @Column(name = "company_id")
    private Long companyId;

    @Column(name = "reason")
    private String reason;

    @Column(name = "deletion_date")
    private LocalDateTime deletionDate;

    @Column(name = "deletion_reason")
    private String deletionReason;

}