package by.iba.company.companies.dto;

import by.iba.common.dto.AddressDTO;
import by.iba.common.dto.core.BaseAbstractReq;
import by.iba.common.validation.annotation.ValidEmail;
import by.iba.common.validation.annotation.ValidPhones;
import by.iba.common.validation.annotation.ValidSite;
import by.iba.company.companies.domain.BusinessType;
import by.iba.company.companies.validation.annotation.ValidUNP;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.Valid;
import javax.validation.constraints.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Getter
@Setter
@NoArgsConstructor
public class CompanyReq extends BaseAbstractReq {

    @NotBlank(message = "validation.company.unp.not_presented")
    @ValidUNP
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

    @NotBlank
    private String description;

    @NotNull(message = "Please enter adderss")
    @Valid
    private AddressDTO address;

    @NotEmpty(message = "validation.company.phone_number.not_presented")
    @ValidPhones
    private List<String> phoneNumbers = new ArrayList<>();

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
    @NotNull(message = "validation.company.foundation_date.not_presented")
    @Past
    private LocalDate foundationDate;

    @NotNull(message = "Business type must selected")
    private BusinessType businessType;

    private Set<Long> companyFeatureIds = new HashSet<>();
}
