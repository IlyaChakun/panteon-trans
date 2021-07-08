package by.iba.exchange.cargo.service;

import by.iba.common.dto.PatchReq;
import by.iba.common.patch.PatchUtil;
import by.iba.exchange.cargo.domain.CargoOffer;
import by.iba.exchange.cargo.dto.CargoOfferResp;
import by.iba.exchange.cargo.dto.CargoOfferReq;
import by.iba.exchange.cargo.dto.CargoSearchCriteriaDTO;
import by.iba.exchange.cargo.dto.mapper.CargoOfferMapper;
import by.iba.exchange.cargo.repository.CargoOfferRepository;
import by.iba.exchange.cargo.specifications.CargoOfferSpecifications;
import by.iba.common.dto.PageWrapper;
import by.iba.common.exception.ResourceNotFoundException;
//import by.iba.repository.UserRepository;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;

@Service
@AllArgsConstructor
@Slf4j
public class CargoOfferServiceImpl implements CargoOfferService {

    private final CargoOfferRepository cargoOfferRepository;
    private final CargoOfferMapper cargoMapper;


    @Transactional
    @CachePut(value = "id", key = "#p0")
    @Override
    public CargoOfferResp save(CargoOfferReq cargoOfferReqDTO) {
        log.info("Start saving the cargo");
        //  String email = userRepository.findByUserId(cargoOfferReqDTO.getUserId()).getEmail();
        CargoOffer cargoOffer = cargoMapper.toEntityFromReq(cargoOfferReqDTO);
        CargoOffer savedCargoOffer = cargoOfferRepository.save(cargoOffer);
        // cargoMailService.sendSaveCargoNotification(email);
        log.info("Finish saving cargo with id =" + savedCargoOffer.getId());
        return cargoMapper.toDto(savedCargoOffer);
    }


    @Transactional
    @CacheEvict(value = "id", key = "#cargoId")
    @Override
    public void delete(Long cargoId) {
        log.info("Start deleting cargo with id = {} ", cargoId);

        CargoOffer cargoOffer = cargoOfferRepository
                .findById(cargoId)
                .orElseThrow(() -> new ResourceNotFoundException("cargo with id = " + cargoId + " not found "));

        cargoOffer.setDeletionDate(LocalDate.now());
        cargoOfferRepository.save(cargoOffer);

        log.info("Cargo with id = {} has been deleted ", cargoId);
    }

    @Override
    public CargoOfferResp findById(Long cargoId) {

        log.info("Start findById cargo with id = {} ", cargoId);

        CargoOffer cargoOffer = cargoOfferRepository.findById(cargoId)
                .orElseThrow(() -> new ResourceNotFoundException("exception.cargo.not_found_by_id" + cargoId));

        log.info("Cargo with id = {} has been find ", cargoId);

        return cargoMapper.toDto(cargoOffer);
    }

    @Override
    public PageWrapper<CargoOfferResp> findAll(Integer page, Integer size, CargoSearchCriteriaDTO cargoSearchCriteriaDTO) {

        log.info("There was a request to findAll cargo with page " + page + "and size" + size);

        Specification<CargoOffer> specification =
                Specification.where(CargoOfferSpecifications
                        .notDeleted())
                        .and(CargoOfferSpecifications.getAllCargoByCountryId(cargoSearchCriteriaDTO.getCountryId()));

        Pageable pageable =
                PageRequest.of(page, size);

        Page<CargoOffer> cargoPage =
                cargoOfferRepository.findAll(specification, pageable);

        log.info("Method response posted to findAll cargo with page " + page + "and size " + size);

        return
                new PageWrapper<>(cargoMapper
                        .toDtoList(cargoPage.toList()),
                        cargoPage.getTotalPages(),
                        cargoPage.getTotalElements());
    }

    @Override
    @Transactional
    public CargoOfferResp partialUpdate(PatchReq patch, Long id) {
        log.info("Partial updating for cargo offer with id = {} ", id);

        CargoOffer cargoOffer = cargoOfferRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("exception.cargo_offer.not_found_by_id"
                        + id));

        CargoOffer patchedOffer = PatchUtil.applyPatchToRequest(patch, cargoOffer);
        CargoOffer savedOffer = cargoOfferRepository.save(patchedOffer);

        return cargoMapper.toDto(savedOffer);
    }

}