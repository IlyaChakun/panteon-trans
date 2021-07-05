package by.iba.exchange.request.dto;

import by.iba.common.dto.core.BaseResp;
import by.iba.common.validation.annotation.ValidEmail;
import by.iba.common.validation.annotation.ValidPhone;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class CustomerResp extends BaseResp {

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
