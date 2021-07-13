package by.iba.company.blacklist.domain;

import by.iba.common.domain.core.FullAbstractEntity;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import java.time.LocalDateTime;

@EqualsAndHashCode(callSuper = false)
@Data
@NoArgsConstructor
@Entity
@Table(name = "blacklist")
public class Blacklist extends FullAbstractEntity {

    @Column(name = "company_id")
    private Long companyId;

    @Column(name = "reason")
    private String reason;

    @Column(name = "deletion_date")
    private LocalDateTime deletionDate;

    @Column(name = "deletion_reason")
    private String deletionReason;

}