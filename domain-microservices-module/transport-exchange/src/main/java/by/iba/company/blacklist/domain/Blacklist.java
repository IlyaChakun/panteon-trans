package by.iba.company.blacklist.domain;

import by.iba.common.domain.BaseEntity;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDateTime;

@EqualsAndHashCode(callSuper = false)
@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "blacklist")
public class Blacklist extends BaseEntity {

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