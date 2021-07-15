package by.iba.common.dto;

import by.iba.common.dto.core.MappableObjectDTO;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Getter
@Setter
@NoArgsConstructor
public class AddressDTO extends MappableObjectDTO {

    @NotNull(message = "validation.company.company.not_presented")
    private Long countryId;

    @NotBlank(message = "validation.company.address.not_presented")
    private String address;

    @Min(value = 1, message = "Apartment number min = 1")
    @Max(value = 5000, message = "Apartment number max = 5000")
    private Integer apartment;
// cityId
}
