package by.iba.blacklist.dto;

import by.iba.common.dto.AbstractDTO;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.NotBlank;


@Setter
@Getter
@NoArgsConstructor
public class BlacklistDTO  extends AbstractDTO {

    @NotBlank(message = "validation.blacklist.company_id.not_presented")
    private Long companyId;

    private Long id;

    private String reasonForAdding;

}
