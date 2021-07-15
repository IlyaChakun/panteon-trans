package by.iba.exchange.cargo.dto;

import by.iba.common.dto.core.BaseAbstractReq;
import by.iba.exchange.common.domain.LoadingPayload;
import by.iba.exchange.common.domain.TemperatureMode;
import by.iba.exchange.common.domain.UnloadingPayload;
import by.iba.exchange.common.dto.LoadingPayloadDTO;
import by.iba.exchange.common.dto.UnloadingPayloadDTO;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.HashSet;
import java.util.Set;

@Getter
@Setter
@NoArgsConstructor
public class CargoOfferReq extends BaseAbstractReq {

    private Long cargoTypeId;

    private String description;

    private CargoDimensionsDTO cargoDimensions;

    private Set<Long> truckBodyTypeIds = new HashSet<>();

    private Set<Long> cargoStowageMethodIds = new HashSet<>();

    private Long customerCompanyId;

    private TemperatureMode temperatureMode = TemperatureMode.A;

    private LoadingPayloadDTO loadingPayload;

    private UnloadingPayloadDTO unloadingPayload;

}
