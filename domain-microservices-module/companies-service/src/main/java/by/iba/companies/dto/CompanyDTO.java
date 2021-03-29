package by.iba.companies.dto;

import by.iba.common.dto.AbstractDTO;
import by.iba.companies.domain.BusinessType;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
@NoArgsConstructor
public class CompanyDTO extends AbstractDTO {

    private String UNP;

    private Long ownerId;

    private String email;

    private String site;

    private String title;

    private String description;

    private String country;

    private String address;

    private String phoneNumber;

    private Date foundationDate;

    private BusinessType businessType;

}
