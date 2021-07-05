package by.iba.exchange.truck.service;

import by.iba.exchange.common.domain.CargoStowageMethod;
import by.iba.exchange.common.domain.TruckBodyType;
import by.iba.exchange.common.dto.PageWrapper;
import by.iba.exchange.common.dto.mapper.LoadingPayloadMapperDTO;
import by.iba.exchange.common.dto.mapper.PaymentMapperDTO;
import by.iba.exchange.common.dto.mapper.UnloadingPayloadMapperDTO;
import by.iba.exchange.common.exception.ResourceNotFoundException;
import by.iba.exchange.common.repository.CargoStowageMethodRepository;
import by.iba.exchange.common.repository.TruckBodyTypeRepository;
import by.iba.exchange.truck.domain.TruckOffer;
import by.iba.exchange.truck.dto.TruckOfferDTO;
import by.iba.exchange.truck.dto.TruckOfferReqDTO;
import by.iba.exchange.truck.dto.mapper.TruckMapperDTO;
import by.iba.exchange.truck.repository.TruckRepository;
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
public class TruckServiceImpl implements TruckService {

    private final LoadingPayloadMapperDTO loadingPayloadMapperDTO;
    private final UnloadingPayloadMapperDTO unloadingPayloadMapperDTO;
    private final CargoStowageMethodRepository cargoStowageMethodRepository;
    private final TruckBodyTypeRepository truckBodyTypeRepository;
    private final PaymentMapperDTO paymentMapperDTO;
    private final TruckRepository truckRepository;
    private final TruckMapperDTO truckMapper;

    @Transactional
    @Override
    public TruckOfferDTO save(TruckOfferReqDTO truckOfferReqDTO) {
        TruckOffer truckOffer = mapToTruck(truckOfferReqDTO);
        TruckOffer saved = truckRepository.save(truckOffer);
        return truckMapper.toDto(saved);
    }

    @Override
    public TruckOfferDTO findById(Long id) {
        TruckOffer truckOffer = truckRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("truck not found"));
        return truckMapper.toDto(truckOffer);
    }

    @Override
    public PageWrapper<TruckOfferDTO> findAll(Integer page, Integer size) {
        Pageable pageable =
                PageRequest.of(page, size);

        Page<TruckOffer> truckPage =
                truckRepository.findAll(pageable);

        return
                new PageWrapper<>(truckMapper
                        .toDtoList(truckPage.toList()),
                        truckPage.getTotalPages(),
                        truckPage.getTotalElements());
    }

    private TruckOffer mapToTruck(TruckOfferReqDTO truckOfferReqDTO) {
        TruckOffer truckOffer = new TruckOffer();

        truckOffer.setCarrierCompanyId(truckOfferReqDTO.getCarrierCompanyId());
        truckOffer.setLoadingPayload(loadingPayloadMapperDTO.toEntity(truckOfferReqDTO.getLoadingPayload()));
        truckOffer.setUnloadingPayload(unloadingPayloadMapperDTO.toEntity(truckOfferReqDTO.getUnloadingPayload()));
        truckOffer.setPayment(paymentMapperDTO.toEntity(truckOfferReqDTO.getPayment()));
        truckOffer.setLoadingPayload(loadingPayloadMapperDTO.toEntity(truckOfferReqDTO.getLoadingPayload()));
        truckOffer.setUnloadingPayload(unloadingPayloadMapperDTO.toEntity(truckOfferReqDTO.getUnloadingPayload()));

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
