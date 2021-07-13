package by.iba.company.review.dto;

import by.iba.common.dto.core.FullAbstractResp;
import by.iba.company.review.domain.CompanyServiceConstants;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Getter
@Setter
@NoArgsConstructor
public class CompanyReviewResp extends FullAbstractResp {
    @NotNull
    private Long userId;

    @NotBlank(message = "validation.company_rating.review_message.not_presented")
    private String reviewMessage;

    @NotNull
    private Long companyId;

    @Min(CompanyServiceConstants.MIN_RATING)
    @Max(CompanyServiceConstants.MAX_RATING)
    private int rating;

}
