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

@EqualsAndHashCode(callSuper = true)
@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@IdClass(CompanyId.class)
@Table(name = "blacklist")
public class Blacklist extends AbstractEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.TABLE)
    @Column(name = "id")
    private Long id;

    @Id
    @Column(name = "company_unp")
    private String UNP;

    @Id
    @Column(name = "owner_id")
    private Long ownerId;

    @Id
    @Column(name = "company_id")
    private Long companyId;

    @Column(name = "reason")
    private String reason;

    @Column(name = "deletion_date")
    private LocalDateTime deletionDate;

}