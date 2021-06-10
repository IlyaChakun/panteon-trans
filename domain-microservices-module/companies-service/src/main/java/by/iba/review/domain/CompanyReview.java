package by.iba.review.domain;


import by.iba.common.domain.BaseEntity;
import by.iba.companies.domain.CompanyId;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import org.checkerframework.checker.units.qual.C;
import org.hibernate.annotations.Filter;
import org.hibernate.annotations.FilterDef;
import org.hibernate.annotations.ParamDef;
import org.hibernate.annotations.SQLDelete;

import javax.persistence.*;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

@EqualsAndHashCode(callSuper = true)
@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "company_review")
public class CompanyReview extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.TABLE)
    @Column(name = "id")
    private Long id;

    @Column(name = "user_id")
    private Long userId;

    @Column(name = "company_id")
    private Long companyId;

    @Column(name = "company_unp")
    private String UNP;

    @Column(name = "owner_id")
    private Long ownerId;

    @Column(name = "review_message")
    private String reviewMessage;

    @Column(name = "rating")
    private int rating;

    @Column(name = "date")
    private LocalDate date = null;

}


