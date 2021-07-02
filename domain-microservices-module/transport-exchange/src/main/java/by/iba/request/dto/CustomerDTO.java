package by.iba.request.dto;

import by.iba.common.dto.BaseDTO;
import by.iba.common.validation.annotation.ValidEmail;
import by.iba.common.validation.annotation.ValidPhone;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Getter
@Setter
@NoArgsConstructor
public class CustomerDTO extends BaseDTO {

    private String UNP;

    @ValidEmail
    private String email;

    private String title;

    private Long countryId;

    private String address;

    @ValidPhone
    private String phoneNumber;

    private String contactName;

}
