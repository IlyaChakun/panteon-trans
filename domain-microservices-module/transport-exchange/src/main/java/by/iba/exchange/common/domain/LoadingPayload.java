package by.iba.exchange.common.domain;

import by.iba.common.domain.core.BaseAbstractEntity;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import java.time.LocalDate;

@Entity
@Table(name = "loading_locations")
@Getter
@Setter
@NoArgsConstructor
public class LoadingPayload extends BaseAbstractEntity {

    @Column(name = "country_id")
    private Long countryId;

    @Column(name = "region_id")
    private Long regionId;

    @Column(name = "city_id")
    private Long cityId;

    @Column(name = "loading_date")
    private LocalDate loadingDate;

}
