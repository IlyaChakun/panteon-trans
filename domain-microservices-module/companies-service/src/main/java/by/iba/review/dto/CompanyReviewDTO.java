package by.iba.review.dto;

import by.iba.common.dto.BaseDTO;
import by.iba.review.domain.Rating;
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
public class CompanyReviewDTO extends BaseDTO {
    @NotNull
    private Long userId;

    @NotBlank(message = "validation.company_rating.review_message.not_presented")
    private String reviewMessage;

    @Min(Rating.MIN_RATING)
    @Max(Rating.MAX_RATING)
    private int rating;

}