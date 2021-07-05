package by.iba.common.entity;


import by.iba.common.domain.core.BaseAbstractEntity;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name = "cities")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class City extends BaseAbstractEntity {

    @Column(name = "city_name", nullable = false, length = 48)
    private String cityName;

    @Column(name = "city_index")
    private String cityIndex;
}
