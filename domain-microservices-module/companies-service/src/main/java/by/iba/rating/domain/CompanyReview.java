package by.iba.rating.domain;

import by.iba.common.domain.AbstractEntity;
import by.iba.companies.domain.CompanyId;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Table(name = "companies_reviews")
@Getter
@Setter
@NoArgsConstructor
public class CompanyReview extends AbstractEntity {
    
    @Id
    @GeneratedValue(strategy = GenerationType.TABLE)
    @Column(name = "id")
    private Long id;
    
    private Long reviewerId;//userId

    private Long companyId;

    private String comment;

    private Integer rating;

    private ReviewType reviewType;

}
