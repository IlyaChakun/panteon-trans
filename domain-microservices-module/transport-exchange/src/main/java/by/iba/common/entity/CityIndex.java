package by.iba.common.entity;

import by.iba.common.domain.BaseEntity;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Embeddable;

@Embeddable
@Getter
@Setter
public class CityIndex extends BaseEntity {

    @Column(name = "city_index")
    private String cityIndex;
}
