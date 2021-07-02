package by.iba.truck.service;

import by.iba.common.domain.CargoStowageMethod;
import by.iba.common.domain.TruckBodyType;
import by.iba.common.dto.PageWrapper;
import by.iba.common.dto.mapper.LoadingLocationMapperDTO;
import by.iba.common.dto.mapper.PaymentMapperDTO;
import by.iba.common.dto.mapper.UnLoadingLocationMapperDTO;
import by.iba.common.exception.ResourceNotFoundException;
import by.iba.common.repository.CargoStowageMethodRepository;
import by.iba.common.repository.TruckBodyTypeRepository;
import by.iba.truck.domain.TruckOffer;
import by.iba.truck.dto.TruckOfferDTO;
import by.iba.truck.dto.TruckOfferReqDTO;
import by.iba.truck.dto.mapper.TruckMapperDTO;
import by.iba.truck.repository.TruckRepository;
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

    private final LoadingLocationMapperDTO loadingLocationMapperDTO;
    private final UnLoadingLocationMapperDTO unLoadingLocationMapperDTO;
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
        truckOffer.setLoadingDate(truckOfferReqDTO.getLoadingDate());
        truckOffer.setUnloadingDate(truckOfferReqDTO.getUnloadingDate());
        truckOffer.setPayment(paymentMapperDTO.toEntity(truckOfferReqDTO.getPayment()));
        truckOffer.setLoadingPayload(loadingLocationMapperDTO.toEntity(truckOfferReqDTO.getLoadingLocation()));
        truckOffer.setUnloadingPayload(unLoadingLocationMapperDTO.toEntity(truckOfferReqDTO.getUnloadingLocation()));

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
