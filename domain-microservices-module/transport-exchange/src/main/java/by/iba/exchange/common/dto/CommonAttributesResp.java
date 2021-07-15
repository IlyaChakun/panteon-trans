package by.iba.exchange.common.dto;

import by.iba.common.dto.core.FullAbstractResp;
import by.iba.exchange.request.entity.TransportationRequestStatus;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.MappedSuperclass;
import java.util.HashSet;
import java.util.Set;

@MappedSuperclass
@Getter
@Setter
public abstract class CommonAttributesResp extends FullAbstractResp {//TODO add validation

    private Long id;

    private LoadingPayloadDTO loadingPayload;

    private UnloadingPayloadDTO unloadingPayload;

    private Set<Long> cargoStowageMethodIds = new HashSet<>();

    private PaymentResp payment;

    private TransportationRequestStatus status;

}
