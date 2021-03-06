package by.iba.exchange.cargo.domain;

import by.iba.exchange.common.domain.CommonAttributes;
import by.iba.exchange.common.domain.TemperatureMode;
import by.iba.exchange.common.domain.TruckBodyType;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "cargo_offers")
@Getter
@Setter
@NoArgsConstructor
public class CargoOffer extends CommonAttributes {

    @ManyToOne(cascade = CascadeType.ALL)
    private CargoType cargoType;

    @Column(name = "description")
    private String description;

    private CargoDimensions cargoDimensions;

    @Column(name = "customer_company_id")
    private Long customerCompanyId;

    @ManyToMany
    @JoinTable(name = "cargo_truck_body_types",
            joinColumns =
                    {
                            @JoinColumn(
                                    name = "cargo_id",
                                    referencedColumnName = "id")},
            inverseJoinColumns = {@JoinColumn(name = "truck_body_type_id",
                    referencedColumnName = "id")})
    private Set<TruckBodyType> truckBodyTypes = new HashSet<>();

    @Column(name = "temperature_mode")
    @Enumerated(value = EnumType.STRING)
    private TemperatureMode temperatureMode;

    @Column(name = "deletion_date")
    private LocalDate deletionDate = null;

}