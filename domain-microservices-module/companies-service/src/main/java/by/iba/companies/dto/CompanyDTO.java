package by.iba.companies.dto;

import by.iba.common.dto.AbstractDTO;
import by.iba.companies.domain.BusinessType;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.time.LocalDate;

@Getter
@Setter
@NoArgsConstructor
public class CompanyDTO extends AbstractDTO {

    @NotNull(message = "validation.company.owner_id_not_presented")
    private Long ownerId;

    @NotBlank(message = "validation.company.unp.not_presented")
    private String UNP;

    private String email;

    private String site;

    private String title;

    private String description;

    private Long countryId;

    private String address;

    private String phoneNumber;

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
    private LocalDate foundationDate;

    private BusinessType businessType;

}