package by.iba.entity;

import by.iba.common.domain.BaseAbstractEntity;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "city_index")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class CityIndex extends BaseAbstractEntity {

    @Column(name = "city_index")
    private String cityIndex;
}
