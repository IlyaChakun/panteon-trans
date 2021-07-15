package by.iba.exchange.truck.service;

import by.iba.common.dto.PageWrapper;
import by.iba.common.dto.PatchReq;
import by.iba.common.exception.ResourceNotFoundException;
import by.iba.common.patch.PatchUtil;
import by.iba.exchange.truck.domain.TruckOffer;
import by.iba.exchange.truck.dto.TruckOfferReq;
import by.iba.exchange.truck.dto.TruckOfferResp;
import by.iba.exchange.truck.dto.mapper.TruckOfferMapper;
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

    private final TruckOfferRepository truckOfferRepository;
    private final TruckOfferMapper truckMapper;

    @Transactional
    @Override
    public TruckOfferResp save(TruckOfferReq truckOfferReqDTO) {
        TruckOffer truckOffer = truckMapper.toEntityFromReq(truckOfferReqDTO);
        TruckOffer saved = truckOfferRepository.save(truckOffer);
        return truckMapper.toDto(saved);
    }

    @Override
    public TruckOfferResp findById(Long id) {
        TruckOffer truckOffer = truckOfferRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("truck not found"));
        return truckMapper.toDto(truckOffer);
    }

    @Override
    public PageWrapper<TruckOfferResp> findAll(Integer page, Integer size) {
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

    @Override
    @Transactional
    public TruckOfferResp partialUpdate(PatchReq patch, Long id) {
        

        TruckOffer offer = truckOfferRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("exception.truck_offer.not_found_by_id"));

        TruckOffer patched = PatchUtil.applyPatchToRequest(patch, offer);
        TruckOffer saved = truckOfferRepository.save(patched);

        return truckMapper.toDto(saved);
    }

}
