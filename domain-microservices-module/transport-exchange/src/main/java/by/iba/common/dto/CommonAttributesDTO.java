package by.iba.common.dto;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.MappedSuperclass;
import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;

@MappedSuperclass
@Getter
@Setter
public abstract class CommonAttributesDTO extends AbstractDTO {//TODO add validation

    private Long id;

    private LoadingLocationDTO loadingLocation;

    private UnLoadingLocationDTO unloadingLocation;

    private Set<Long> cargoStowageMethodIds = new HashSet<>();

    private PaymentDTO payment;

}
