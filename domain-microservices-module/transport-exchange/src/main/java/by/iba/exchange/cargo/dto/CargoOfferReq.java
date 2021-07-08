package by.iba.exchange.cargo.dto;

import by.iba.common.dto.core.BaseAbstractReq;
import by.iba.exchange.common.domain.LoadingPayload;
import by.iba.exchange.common.domain.TemperatureMode;
import by.iba.exchange.common.domain.UnloadingPayload;
import by.iba.exchange.common.dto.CommonAttributesResp;
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

    private CargoDimensionsResp cargoDimensions;

    private Set<Long> truckBodyTypeIds = new HashSet<>();

    private Set<Long> cargoStowageMethodIds = new HashSet<>();

    private Long userId;

    private Long customerCompanyId;

    private TemperatureMode temperatureMode;

    private LoadingPayload loadingPayload;

    private UnloadingPayload unloadingPayload;

}
