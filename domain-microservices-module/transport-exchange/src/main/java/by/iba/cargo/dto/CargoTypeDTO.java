package by.iba.cargo.dto;

import by.iba.common.dto.BaseAbstractDTO;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class CargoTypeDTO extends BaseAbstractDTO {

    private String typeName;
}
