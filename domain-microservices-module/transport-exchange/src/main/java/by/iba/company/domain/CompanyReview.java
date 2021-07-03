package by.iba.company.domain;

import by.iba.common.domain.BaseEntity;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Table(name = "companies_reviews")
@Getter
@Setter
@NoArgsConstructor
public class CompanyReview extends BaseEntity {
    
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
