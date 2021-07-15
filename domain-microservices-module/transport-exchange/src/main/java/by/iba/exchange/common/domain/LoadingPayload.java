package by.iba.exchange.common.domain;

import by.iba.common.domain.Address;
import by.iba.common.domain.core.FullAbstractEntity;
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
public class LoadingPayload extends FullAbstractEntity {

    private Address address;

    @Column(name = "loading_date")
    private LocalDate loadingDate;

}
