package by.iba.common.entity;


import by.iba.common.domain.core.FullAbstractEntity;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "cities")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class City extends FullAbstractEntity {

    @Column(name = "city_name", nullable = false, length = 48)
    private String cityName;

    @Column(name = "city_index")
    private String cityIndex;
}
