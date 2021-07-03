package by.iba.company.domain;

import by.iba.common.domain.BaseEntity;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "companies_rating")
@Getter
@Setter
@NoArgsConstructor
public class CompanyRating extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.TABLE)
    @Column(name = "id")
    private Long id;

    private Long companyId;

    private Integer currentRating;

    @OneToMany
    private List<CompanyReview> companyReviews = new ArrayList<>();


}
