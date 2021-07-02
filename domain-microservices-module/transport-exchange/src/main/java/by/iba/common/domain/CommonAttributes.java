package by.iba.common.domain;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;

@MappedSuperclass
@Getter
@Setter
public abstract class CommonAttributes extends BaseAbstractEntity {

    @ManyToOne(cascade = CascadeType.ALL)
    private LoadingLocation loadingLocation;

    @ManyToOne(cascade = CascadeType.ALL)
    private UnLoadingLocation unloadingLocation;

    @ManyToMany
    private Set<CargoStowageMethod> cargoStowageMethods = new HashSet<>(); // способ загрузки

    @Column(name = "loading_date")
    private LocalDate loadingDate;

    @Column(name = "unloading_date")
    private LocalDate unloadingDate;

    private Payment payment;
}
