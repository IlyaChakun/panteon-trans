package by.iba.company.blacklist.dto;

import by.iba.common.dto.core.FullAbstractResp;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Null;
import java.time.LocalDateTime;


@Setter
@Getter
@NoArgsConstructor
public class BlacklistResp extends FullAbstractResp {

    @NotBlank(message = "validation.blacklist.company_id.not_presented")
    private Long companyId;

    @NotNull
    private Long id;

    @NotBlank(message = "validation.blacklist.reason_is_empty")
    private String reason;

    @Null
    private String deletionReason;

    @Null
    private LocalDateTime deletionDate;

}
