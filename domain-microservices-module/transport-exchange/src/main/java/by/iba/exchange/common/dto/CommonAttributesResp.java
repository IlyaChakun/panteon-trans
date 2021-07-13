package by.iba.exchange.common.dto;

import by.iba.common.dto.core.BaseAbstractResp;
import by.iba.exchange.request.entity.TransportationRequestStatus;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.MappedSuperclass;
import java.util.HashSet;
import java.util.Set;

@MappedSuperclass
@Getter
@Setter
public abstract class CommonAttributesResp extends BaseAbstractResp {//TODO add validation

    private Long id;

    private LoadingPayloadResp loadingPayload;

    private UnloadingPayloadResp unloadingPayload;

    private Set<Long> cargoStowageMethodIds = new HashSet<>();

    private PaymentResp payment;

    private TransportationRequestStatus status;

}
