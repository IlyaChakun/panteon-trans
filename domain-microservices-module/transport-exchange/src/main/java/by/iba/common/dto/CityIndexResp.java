package by.iba.common.dto;

import by.iba.common.dto.core.BaseAbstractResp;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class CityIndexResp extends BaseAbstractResp {

    private String cityIndex;
}
