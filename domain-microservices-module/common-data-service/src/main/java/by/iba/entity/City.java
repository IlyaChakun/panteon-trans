package by.iba.entity;


import by.iba.common.domain.BaseAbstractEntity;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Table(name = "cities")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class City extends BaseAbstractEntity {

    @Column(name = "city_name", nullable = false, length = 48)
    private String cityName;

    @ManyToOne(cascade = CascadeType.ALL)
    private CityIndex cityIndex;
}
