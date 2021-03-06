package by.iba.company.blacklist.dto;

import by.iba.common.dto.core.FullAbstractResp;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.NotBlank;
import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
public class BlacklistDeleteResp extends FullAbstractResp {

    @NotBlank(message = "validation.blacklist.reason_ise_empty")
    private String deletionReason;

    private LocalDateTime deletionDate;

}
