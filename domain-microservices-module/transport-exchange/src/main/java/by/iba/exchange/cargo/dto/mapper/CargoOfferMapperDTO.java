package by.iba.exchange.cargo.dto.mapper;

import by.iba.common.mapper.core.FullAbstractMapper;
import by.iba.exchange.cargo.domain.CargoOffer;
import by.iba.exchange.cargo.dto.CargoOfferReq;
import by.iba.exchange.cargo.dto.CargoOfferResp;
import by.iba.exchange.cargo.repository.CargoTypeRepository;
import by.iba.exchange.common.domain.CargoStowageMethod;
import by.iba.exchange.common.dto.mapper.TruckBodyTypeMapperDTO;
import by.iba.exchange.common.repository.CargoStowageMethodRepository;
import by.iba.exchange.common.repository.TruckBodyTypeRepository;
import by.iba.exchange.truck.domain.TruckOffer;
import by.iba.exchange.truck.dto.TruckOfferResp;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.util.stream.Collectors;

@Component
public class CargoOfferMapperDTO extends FullAbstractMapper<CargoOffer, CargoOfferResp, CargoOfferReq> {

    private final TruckBodyTypeRepository truckBodyTypeRepository;
    private final CargoStowageMethodRepository cargoStowageMethodRepository;
    private final CargoTypeRepository cargoTypeRepository;

    @Autowired
    public CargoOfferMapperDTO(TruckBodyTypeRepository truckBodyTypeRepository,
                               CargoStowageMethodRepository cargoStowageMethodRepository,
                               CargoTypeRepository cargoTypeRepository) {

        super(CargoOffer.class, CargoOfferResp.class);
        this.truckBodyTypeRepository = truckBodyTypeRepository;
        this.cargoStowageMethodRepository = cargoStowageMethodRepository;
        this.cargoTypeRepository = cargoTypeRepository;
    }

    @PostConstruct
    public void setupMapper() {

        mapper.createTypeMap(CargoOffer.class, CargoOfferResp.class)
                .addMappings(m -> m.skip(CargoOfferResp::setCargoStowageMethodIds))
                .setPostConverter(toDtoConverter());
        mapper.createTypeMap(CargoOfferReq.class, CargoOffer.class)
                .addMappings(m -> m.skip(CargoOffer::setCargoStowageMethods))
                .addMappings(m->m.skip(CargoOffer::setTruckBodyTypes))
                .addMappings(m->m.skip(CargoOffer::setCargoType))
                .setPostConverter(toEntityFromReqConverter());
    }

    @Override
    protected void mapSpecificFields(final CargoOffer source, final CargoOfferResp destination) {

        destination.setCargoStowageMethodIds(
                source.getCargoStowageMethods()
                        .stream()
                        .map(CargoStowageMethod::getId)
                        .collect(Collectors.toSet())
        );
    }

    @Override
    protected void mapSpecificFields(final CargoOfferReq source, final CargoOffer destination) {
        // mapping
        destination.setCargoType(cargoTypeRepository.getOne(source.getCargoTypeId()));
        destination.setCargoStowageMethods(
                source.getCargoStowageMethodIds()
                        .stream()
                        .map(cargoStowageMethodRepository::getOne)
                .collect(Collectors.toSet())
        );
        destination.setTruckBodyTypes(
                source.getTruckBodyTypeIds()
                        .stream()
                        .map(truckBodyTypeRepository::getOne)
                        .collect(Collectors.toSet())
        );

    }

}
