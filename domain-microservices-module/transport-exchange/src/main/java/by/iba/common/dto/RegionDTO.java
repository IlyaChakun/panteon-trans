package by.iba.common.dto;


import by.iba.common.dto.BaseAbstractDTO;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class RegionDTO extends BaseAbstractDTO {

    private String regionName;

}