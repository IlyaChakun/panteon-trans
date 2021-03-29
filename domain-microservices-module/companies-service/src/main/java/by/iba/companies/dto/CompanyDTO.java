package by.iba.companies.dto;

import by.iba.common.dto.AbstractDTO;
import by.iba.companies.domain.BusinessType;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
@NoArgsConstructor
public class CompanyDTO extends AbstractDTO {

    private Long companyId;

    private String UNP;

    private Long ownerId;

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
