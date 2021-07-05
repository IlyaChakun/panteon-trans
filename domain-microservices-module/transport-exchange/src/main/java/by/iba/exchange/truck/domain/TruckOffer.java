package by.iba.exchange.truck.domain;

import by.iba.exchange.common.domain.CommonAttributes;
import by.iba.exchange.common.domain.TruckBodyType;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Table(name = "trucks_offres")
@Getter
@Setter
@NoArgsConstructor
public class TruckOffer extends CommonAttributes {

    @ManyToOne(cascade = CascadeType.ALL)
    private TruckBodyType truckBodyType;

    private TruckDimensions truckDimensions;

    @Column(name = "carrier_company_id")
    private Long carrierCompanyId;

}
