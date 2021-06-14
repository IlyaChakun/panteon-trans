package by.iba.blacklist.dto;

import by.iba.common.dto.AbstractDTO;
import by.iba.companies.validation.annotation.ValidUNP;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.NotBlank;
import java.time.LocalDateTime;


@Setter
@Getter
@NoArgsConstructor
public class BlacklistDTO  extends AbstractDTO {

    @NotBlank(message = "validation.blacklist.company_id.not_presented")
    private Long companyId;

    private Long id;

    @NotBlank
    private String reason;

    @NotBlank
    private String deletionReason;

    private LocalDateTime deletionDate;


}
