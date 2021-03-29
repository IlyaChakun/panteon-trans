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
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
public class CompanyDTO extends AbstractDTO {

    private Long companyId;

    @NotBlank(message = "validation.company.unp.not_presented")
    private String UNP;

    @NotNull(message = "validation.company.owner_id_not_presented")
    private Long ownerId;

    private String email;

    private String site;

    private String title;

    private String description;

    private Long countryId;

    private String address;

    private List<String> phoneNumbers = new ArrayList<>();

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
    private LocalDate foundationDate;

    private BusinessType businessType;

}
