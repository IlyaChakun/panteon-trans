package by.iba.exchange.cargo.dto;

import by.iba.common.dto.core.BaseAbstractReq;
import by.iba.exchange.common.domain.TemperatureMode;
import by.iba.exchange.common.dto.LoadingPayloadDTO;
import by.iba.exchange.common.dto.UnloadingPayloadDTO;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.Valid;
import java.util.HashSet;
import java.util.Set;

@Getter
@Setter
@NoArgsConstructor
public class CargoOfferReq extends BaseAbstractReq {

    private Long cargoTypeId;//TODO VALIDATION for all

    private String description;

    @Valid
    private CargoDimensionsDTO cargoDimensions;

    private Set<Long> truckBodyTypeIds = new HashSet<>();

    private Set<Long> cargoStowageMethodIds = new HashSet<>();

    private Long customerCompanyId;

    private TemperatureMode temperatureMode = TemperatureMode.A;

    @Valid
    private LoadingPayloadDTO loadingPayload;

    @Valid
    private UnloadingPayloadDTO unloadingPayload;

}
