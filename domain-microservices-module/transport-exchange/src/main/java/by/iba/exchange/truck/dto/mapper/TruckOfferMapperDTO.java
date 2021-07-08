package by.iba.exchange.truck.dto.mapper;

import by.iba.common.mapper.core.FullAbstractMapper;
import by.iba.exchange.common.repository.CargoStowageMethodRepository;
import by.iba.exchange.common.repository.TruckBodyTypeRepository;
import by.iba.exchange.truck.domain.TruckOffer;
import by.iba.exchange.truck.dto.TruckOfferReq;
import by.iba.exchange.truck.dto.TruckOfferResp;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.util.stream.Collectors;

@Component
public class TruckOfferMapperDTO extends FullAbstractMapper<TruckOffer, TruckOfferResp, TruckOfferReq> {

    private final CargoStowageMethodRepository cargoStowageMethodRepository;
    private final TruckBodyTypeRepository truckBodyTypeRepository;


    @Autowired
    public TruckOfferMapperDTO(CargoStowageMethodRepository cargoStowageMethodRepository,
                               TruckBodyTypeRepository truckBodyTypeRepository) {
        super(TruckOffer.class, TruckOfferResp.class);
        this.cargoStowageMethodRepository = cargoStowageMethodRepository;
        this.truckBodyTypeRepository = truckBodyTypeRepository;
    }


    @PostConstruct
    public void setupMapper() {


        mapper.createTypeMap(TruckOfferReq.class, TruckOffer.class)
                .addMappings(m -> m.skip(TruckOffer::setCargoStowageMethods))
                .addMappings(m -> m.skip(TruckOffer::setTruckBodyType))
                .setPostConverter(toEntityFromReqConverter());
    }

    @Override
    protected void mapSpecificFields(final TruckOfferReq source, final TruckOffer destination) {
        // mapping
        destination.setTruckBodyType(truckBodyTypeRepository.getOne(source.getTruckBodyTypeId()));
        destination.setCargoStowageMethods(
                source.getCargoStowageMethodIds()
                        .stream().map(cargoStowageMethodRepository::getOne)
                        .collect(Collectors.toSet())
        );
    }
}
