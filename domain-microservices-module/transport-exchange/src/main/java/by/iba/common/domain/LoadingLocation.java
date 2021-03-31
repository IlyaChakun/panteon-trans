package by.iba.common.domain;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "loading_locations")
@Getter
@Setter
@NoArgsConstructor
public class LoadingLocation extends BaseAbstractEntity {

    @Column(name = "country_id")
    private Long countryId;

    @Column(name = "region_id")
    private Long regionId;

    @Column(name = "city_id")
    private Long cityId;

}
