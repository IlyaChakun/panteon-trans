package by.iba.exchange.request.dto;

import by.iba.exchange.common.dto.BaseDTO;
import by.iba.exchange.common.validation.annotation.ValidEmail;
import by.iba.exchange.common.validation.annotation.ValidPhone;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

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
