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
@Table(name = "countries")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Country extends FullAbstractEntity {

    @Column(name = "country_name", nullable = false, length = 48)
    private String countryName;
}
