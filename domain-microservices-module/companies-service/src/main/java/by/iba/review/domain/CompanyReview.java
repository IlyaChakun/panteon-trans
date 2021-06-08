package by.iba.review.domain;


import by.iba.common.domain.BaseEntity;
import by.iba.companies.domain.CompanyId;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.Filter;
import org.hibernate.annotations.FilterDef;
import org.hibernate.annotations.ParamDef;
import org.hibernate.annotations.SQLDelete;

import javax.persistence.*;
import java.time.LocalDate;

@EqualsAndHashCode(callSuper = true)
@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "company_review")
@IdClass(CompanyId.class)
@SQLDelete(sql = "UPDATE company_review SET date = GETDATE() WHERE id=?")
public class CompanyReview extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.TABLE)
    @Column(name = "id")
    private Long id;

    @Id
    @Column(name = "user_id")
    private Long userId;

    @Id
    @Column(name = "company_id")
    private Long companyId;

    @Column(name = "review_message")
    private String reviewMessage;

    @Column(name = "rating")
    private int rating;

    @Column(name = "date")
    private LocalDate date = null;

}



