package by.iba.exchange.request.service;

import by.iba.common.dto.PageWrapper;
import by.iba.common.dto.PatchReq;
import by.iba.common.exception.ResourceNotFoundException;
import by.iba.common.patch.PatchUtil;
import by.iba.exchange.common.dto.mapper.LoadingPayloadMapperDTO;
import by.iba.exchange.common.dto.mapper.UnloadingPayloadMapperDTO;
import by.iba.exchange.request.dto.TransportationReqResp;
import by.iba.exchange.request.dto.mapper.CarrierMapperDTO;
import by.iba.exchange.request.dto.mapper.CustomerMapperDTO;
import by.iba.exchange.request.dto.mapper.TransportationRequestMapperDTO;
import by.iba.exchange.request.entity.TransportationReq;
import by.iba.exchange.request.repository.CarrierRepository;
import by.iba.exchange.request.repository.CustomerRepository;
import by.iba.exchange.request.repository.TransportationReqRepository;
import by.iba.exchange.request.specifications.TransportationReqSpecifications;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service
@AllArgsConstructor
@Slf4j
public class TransportationReqServiceImpl implements TransportationReqService {

    private final TransportationReqRepository transportationReqRepository;
    private final CarrierRepository carrierRepository;
    private final CustomerRepository customerRepository;
    private final CustomerMapperDTO customerMapperDTO;
    private final CarrierMapperDTO carrierMapperDTO;
    private final TransportationRequestMapperDTO transportationRequestMapperDTO;
    private final LoadingPayloadMapperDTO loadingPayloadMapperDTO;
    private final UnloadingPayloadMapperDTO unloadingPayloadMapperDTO;


    @Override
    @Transactional
    public TransportationReqResp save(TransportationReqResp transportationRequestDTO) {

        TransportationReq transportationReq = mapToRequest(transportationRequestDTO);
        TransportationReq savedRequest = transportationReqRepository.save(transportationReq);

        return transportationRequestMapperDTO.toDto(savedRequest);
    }

    @Override
    public TransportationReqResp findById(Long id) {

        TransportationReq request = transportationReqRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("exception.request.not_found_by_id" + id));


        return transportationRequestMapperDTO.toDto(request);
    }

    @Override
    public PageWrapper<TransportationReqResp> findAllByCarrierId(Integer page, Integer size, Long carrierId) {

        Specification<TransportationReq> specification = Specification
                .where(TransportationReqSpecifications.getRequestsByCarrierId(carrierId));

        final Pageable pageable =
                PageRequest.of(page, size);

        final Page<TransportationReq> requests =
                transportationReqRepository.findAll(specification, pageable);

        return
                new PageWrapper<>(transportationRequestMapperDTO
                        .toDtoList(requests.toList()),
                        requests.getTotalPages(),
                        requests.getTotalElements());


    }

    @Override
    public PageWrapper<TransportationReqResp> findAllByCustomerId(Integer page, Integer size, Long customerId) {

        Specification<TransportationReq> specification = Specification
                .where(TransportationReqSpecifications.getRequestsByCustomerId(customerId));

        final Pageable pageable =
                PageRequest.of(page, size);

        final Page<TransportationReq> requests =
                transportationReqRepository.findAll(specification, pageable);

        return
                new PageWrapper<>(transportationRequestMapperDTO
                        .toDtoList(requests.toList()),
                        requests.getTotalPages(),
                        requests.getTotalElements());

    }

    @Override
    public PageWrapper<TransportationReqResp> findAll(Integer page, Integer size) {


        final Pageable pageable =
                PageRequest.of(page, size);

        final Page<TransportationReq> requests =
                transportationReqRepository.findAll(pageable);

        return
                new PageWrapper<>(transportationRequestMapperDTO
                        .toDtoList(requests.toList()),
                        requests.getTotalPages(),
                        requests.getTotalElements());

    }

    @Override
    @Transactional
    public TransportationReqResp partialUpdate(PatchReq patch, Long id) {
        log.info("Updating request with id = {}", id);

        TransportationReq transportationReq =
                transportationReqRepository.findById(id)
                        .orElseThrow(() -> new ResourceNotFoundException("exception.request.not_found_by_id"
                                + id));

        TransportationReq mappedReq = PatchUtil.applyPatchToRequest(patch, transportationReq);
        TransportationReq savedReq =  transportationReqRepository.save(mappedReq);

        return transportationRequestMapperDTO.toDto(savedReq);


    }

    private TransportationReq mapToRequest(TransportationReqResp transportationRequestDTO) {
        TransportationReq transportationReq = new TransportationReq();

        transportationReq.setLoadingPayload(loadingPayloadMapperDTO
                .toEntity(transportationRequestDTO.getLoadingPayload()));

        transportationReq.setUnloadingPayload(unloadingPayloadMapperDTO
                .toEntity(transportationRequestDTO.getUnloadingPayload()));

        final String carrierUNP = transportationRequestDTO.getCarrier().getUNP();
        final String customerUNP = transportationRequestDTO.getCustomer().getUNP();

        if (!carrierRepository.existsCarrierByUNP(carrierUNP)) {

            transportationReq.setCarrier(carrierMapperDTO.toEntity(transportationRequestDTO.getCarrier()));

        } else {
            transportationReq.setCarrier(carrierRepository.findCarrierByUNP(carrierUNP).get());
        }

        if (!customerRepository.existsCustomerByUNP(customerUNP)) {
            transportationReq.setCustomer(customerMapperDTO.toEntity(transportationRequestDTO.getCustomer()));
        } else {
            transportationReq.setCustomer(customerRepository.findCustomerByUNP(customerUNP).get());
        }

        transportationReq.setAdditionalInformation(transportationRequestDTO.getAdditionalInformation());
        transportationReq.setStatus(transportationRequestDTO.getStatus());
        transportationReq.setCargoOfferId(transportationRequestDTO.getCargoOfferId());
        transportationReq.setTruckOfferId(transportationRequestDTO.getTruckOfferId());
        transportationReq.setFreightCost(transportationRequestDTO.getFreightCost());

        return transportationReq;
    }

}
