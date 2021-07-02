package by.iba.common.domain;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@MappedSuperclass
@Getter
@Setter
public abstract class CommonAttributes extends BaseAbstractEntity {

    @ManyToOne(cascade = CascadeType.ALL)
    private LoadingPayload loadingPayload;

    @ManyToOne(cascade = CascadeType.ALL)
    private UnloadingPayload unloadingPayload;

    @ManyToMany
    private Set<CargoStowageMethod> cargoStowageMethods = new HashSet<>(); // способ загрузки

    private Payment payment;
}
