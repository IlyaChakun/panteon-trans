package by.iba.dto;

import by.iba.common.dto.core.BaseAbstractResp;
import by.iba.common.validation.annotation.ValidEmail;
import by.iba.common.validation.annotation.ValidPhones;
import by.iba.common.validation.annotation.ValidSite;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Past;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
public class CompanyResp extends BaseAbstractResp {

    private Long companyId;

    @NotBlank(message = "validation.company.unp.not_presented")
    private String unp;

    @NotNull(message = "validation.company.owner_id.not_presented")
    private Long ownerId;

    @NotBlank(message = "validation.company.email.not_presented")
    @ValidEmail
    private String email;

    @ValidSite
    private String site;

    @NotBlank(message = "validation.company.title.not_presented")
    private String title;

    private String description;

    @NotNull(message = "validation.company.company.not_presented")
    private CountryResp country;

    @NotBlank(message = "validation.company.address.not_presented")
    private String address;

    @NotEmpty(message = "validation.company.phone_number.not_presented")
    @ValidPhones
    private List<String> phoneNumbers = new ArrayList<>();

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
    @NotNull(message = "validation.company.foundation_date.not_presented")
    @Past
    private LocalDate foundationDate;

    private BusinessType businessType;

}
