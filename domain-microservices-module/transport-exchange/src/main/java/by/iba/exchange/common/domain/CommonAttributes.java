package by.iba.exchange.common.domain;

import by.iba.common.domain.core.FullAbstractEntity;
import by.iba.exchange.request.entity.TransportationRequestStatus;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@MappedSuperclass
@Getter
@Setter
public abstract class CommonAttributes extends FullAbstractEntity {

    @ManyToOne(cascade = CascadeType.ALL)
    private LoadingPayload loadingPayload;

    @ManyToOne(cascade = CascadeType.ALL)
    private UnloadingPayload unloadingPayload;

    @ManyToMany
    private Set<CargoStowageMethod> cargoStowageMethods = new HashSet<>(); // способ загрузки

    @Enumerated
    private TransportationRequestStatus status = TransportationRequestStatus.ACTIVE;//default status is active

}
