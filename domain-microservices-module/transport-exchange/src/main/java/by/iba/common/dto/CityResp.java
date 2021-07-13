package by.iba.common.dto;


import by.iba.common.dto.core.FullAbstractResp;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class CityResp extends FullAbstractResp {

    private String cityName;

}
