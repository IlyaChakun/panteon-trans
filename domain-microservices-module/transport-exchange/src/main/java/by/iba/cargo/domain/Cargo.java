package by.iba.cargo.domain;

import by.iba.common.domain.CommonAttributes;
import by.iba.common.domain.TruckBodyType;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "cargo")
@Getter
@Setter
@NoArgsConstructor
public class Cargo extends CommonAttributes {

    @ManyToOne(cascade = CascadeType.ALL)
    private CargoType cargoType;

    @Column(name = "description")
    private String description;

    private CargoDimensions cargoDimensions;

    @Column(name = "user_Id")
    private Long userId;

    @ManyToMany
    @JoinTable(name = "cargo_truck_body_types",
            joinColumns =
                    {
                            @JoinColumn(
                                    name = "cargo_id",
                                    referencedColumnName = "id")},
            inverseJoinColumns = {@JoinColumn(name = "truck_body_type_id",
                    referencedColumnName = "id")})
    private Set<TruckBodyType> truckBodyTypes = new HashSet<>(); //типы кузова

    @Column(name = "deletion_date")
    private LocalDate deletionDate = null;

}