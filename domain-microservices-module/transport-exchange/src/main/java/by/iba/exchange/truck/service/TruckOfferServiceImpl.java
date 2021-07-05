package by.iba.exchange.truck.service;

import by.iba.common.dto.PageWrapper;
import by.iba.common.exception.ResourceNotFoundException;
import by.iba.exchange.common.domain.CargoStowageMethod;
import by.iba.exchange.common.domain.TruckBodyType;
import by.iba.exchange.common.dto.mapper.LoadingPayloadMapperDTO;
import by.iba.exchange.common.dto.mapper.PaymentMapperDTO;
import by.iba.exchange.common.dto.mapper.UnloadingPayloadMapperDTO;
import by.iba.exchange.common.repository.CargoStowageMethodRepository;
import by.iba.exchange.common.repository.TruckBodyTypeRepository;
import by.iba.exchange.truck.domain.TruckOffer;
import by.iba.exchange.truck.dto.TruckOfferDTO;
import by.iba.exchange.truck.dto.TruckOfferReqDTO;
import by.iba.exchange.truck.dto.mapper.TruckOfferMapperDTO;
import by.iba.exchange.truck.repository.TruckOfferRepository;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service
@AllArgsConstructor
@Slf4j
public class TruckOfferServiceImpl implements TruckOfferService {

    private final LoadingPayloadMapperDTO loadingPayloadMapper;
    private final UnloadingPayloadMapperDTO unloadingPayloadMapper;
    private final CargoStowageMethodRepository cargoStowageMethodRepository;
    private final TruckBodyTypeRepository truckBodyTypeRepository;
    private final PaymentMapperDTO paymentMapperDTO;
    private final TruckOfferRepository truckOfferRepository;
    private final TruckOfferMapperDTO truckMapper;

    @Transactional
    @Override
    public TruckOfferDTO save(TruckOfferReqDTO truckOfferReqDTO) {
        TruckOffer truckOffer = mapToTruck(truckOfferReqDTO);
        TruckOffer saved = truckOfferRepository.save(truckOffer);
        return truckMapper.toDto(saved);
    }

    @Override
    public TruckOfferDTO findById(Long id) {
        TruckOffer truckOffer = truckOfferRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("truck not found"));
        return truckMapper.toDto(truckOffer);
    }

    @Override
    public PageWrapper<TruckOfferDTO> findAll(Integer page, Integer size) {
        Pageable pageable =
                PageRequest.of(page, size);

        Page<TruckOffer> truckPage =
                truckOfferRepository.findAll(pageable);

        return
                new PageWrapper<>(truckMapper
                        .toDtoList(truckPage.toList()),
                        truckPage.getTotalPages(),
                        truckPage.getTotalElements());
    }

    private TruckOffer mapToTruck(TruckOfferReqDTO truckOfferReqDTO) {
        TruckOffer truckOffer = new TruckOffer();

        truckOffer.setCarrierCompanyId(truckOfferReqDTO.getCarrierCompanyId());
//        truckOffer.setLoadingDate(truckOfferReqDTO.getLoadingDate());
//        truckOffer.setUnloadingDate(truckOfferReqDTO.getUnloadingDate());
        truckOffer.setPayment(paymentMapperDTO.toEntity(truckOfferReqDTO.getPayment()));
        truckOffer.setLoadingPayload(loadingPayloadMapper.toEntity(truckOfferReqDTO.getLoadingPayload()));
        truckOffer.setUnloadingPayload(unloadingPayloadMapper.toEntity(truckOfferReqDTO.getUnloadingPayload()));

        TruckBodyType truckBodyType = truckBodyTypeRepository.findById(truckOfferReqDTO.getTruckBodyTypeId())
                .orElseThrow(() -> new ResourceNotFoundException("TruckBodyType with id =" + truckOfferReqDTO.getTruckBodyTypeId() + " not found"));
        truckOffer.setTruckBodyType(truckBodyType);

        for (Long id : truckOfferReqDTO.getCargoStowageMethodIds()) {
            CargoStowageMethod cargoStowageMethod = cargoStowageMethodRepository.findById(id)
                    .orElseThrow(() -> new ResourceNotFoundException("CargoStowageMethod with id =" + id + " not found"));
            truckOffer.getCargoStowageMethods().add(cargoStowageMethod);
        }
        return truckOffer;
    }
}
