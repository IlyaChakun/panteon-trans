package by.iba.exchange.request.service;

import by.iba.common.dto.PageWrapper;
import by.iba.common.dto.PatchReq;
import by.iba.common.exception.ResourceNotFoundException;
import by.iba.common.exception.ServiceException;
import by.iba.common.patch.PatchUtil;
import by.iba.exchange.common.dto.mapper.LoadingPayloadMapperDTO;
import by.iba.exchange.common.dto.mapper.UnloadingPayloadMapperDTO;
import by.iba.exchange.request.dto.TransportationRequestResp;
import by.iba.exchange.request.dto.mapper.CarrierMapperDTO;
import by.iba.exchange.request.dto.mapper.CustomerMapperDTO;
import by.iba.exchange.request.dto.mapper.TransportationRequestMapperDTO;
import by.iba.exchange.request.entity.TransportationRequest;
import by.iba.exchange.request.repository.CarrierRepository;
import by.iba.exchange.request.repository.CustomerRepository;
import by.iba.exchange.request.repository.TransportationRequestRepository;
import by.iba.exchange.request.specifications.TransportationRequestSpecifications;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.github.fge.jsonpatch.JsonPatch;
import com.github.fge.jsonpatch.JsonPatchException;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service
@AllArgsConstructor
@Slf4j
public class TransportationRequestServiceImpl implements TransportationRequestService {

    private final TransportationRequestRepository transportationRequestRepository;
    private final CarrierRepository carrierRepository;
    private final CustomerRepository customerRepository;
    private final CustomerMapperDTO customerMapperDTO;
    private final CarrierMapperDTO carrierMapperDTO;
    private final TransportationRequestMapperDTO transportationRequestMapperDTO;
    private final LoadingPayloadMapperDTO loadingPayloadMapperDTO;
    private final UnloadingPayloadMapperDTO unloadingPayloadMapperDTO;


    @Override
    @Transactional
    public TransportationRequestResp save(TransportationRequestResp transportationRequestDTO) {

        TransportationRequest transportationRequest = mapToRequest(transportationRequestDTO);
        TransportationRequest savedRequest = transportationRequestRepository.save(transportationRequest);

        return transportationRequestMapperDTO.toDto(savedRequest);
    }

    @Override
    public TransportationRequestResp findById(Long id) {

        TransportationRequest request = transportationRequestRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("exception.request.not_found_by_id" + id));


        return transportationRequestMapperDTO.toDto(request);
    }

    @Override
    public PageWrapper<TransportationRequestResp> findAllByCarrierId(Integer page, Integer size, Long carrierId) {

        Specification<TransportationRequest> specification = Specification
                .where(TransportationRequestSpecifications.getRequestsByCarrierId(carrierId));

        final Pageable pageable =
                PageRequest.of(page, size);

        final Page<TransportationRequest> requests =
                transportationRequestRepository.findAll(specification, pageable);

        return
                new PageWrapper<>(transportationRequestMapperDTO
                        .toDtoList(requests.toList()),
                        requests.getTotalPages(),
                        requests.getTotalElements());


    }

    @Override
    public PageWrapper<TransportationRequestResp> findAllByCustomerId(Integer page, Integer size, Long customerId) {

        Specification<TransportationRequest> specification = Specification
                .where(TransportationRequestSpecifications.getRequestsByCustomerId(customerId));

        final Pageable pageable =
                PageRequest.of(page, size);

        final Page<TransportationRequest> requests =
                transportationRequestRepository.findAll(specification, pageable);

        return
                new PageWrapper<>(transportationRequestMapperDTO
                        .toDtoList(requests.toList()),
                        requests.getTotalPages(),
                        requests.getTotalElements());

    }

    @Override
    public PageWrapper<TransportationRequestResp> findAll(Integer page, Integer size) {


        final Pageable pageable =
                PageRequest.of(page, size);

        final Page<TransportationRequest> requests =
                transportationRequestRepository.findAll(pageable);

        return
                new PageWrapper<>(transportationRequestMapperDTO
                        .toDtoList(requests.toList()),
                        requests.getTotalPages(),
                        requests.getTotalElements());

    }

    @Override
    @Transactional
    public TransportationRequestResp partialUpdate(PatchReq patch, Long id) {
        log.info("Updating request with id = {}", id);

        TransportationRequest transportationRequest =
                transportationRequestRepository.findById(id)
                        .orElseThrow(() -> new ResourceNotFoundException("exception.request.not_found_by_id"
                                + id));

        TransportationRequest requestResp = PatchUtil.applyPatchToRequest(patch, transportationRequest);
        TransportationRequest savedRequest =  transportationRequestRepository.save(requestResp);

        return transportationRequestMapperDTO.toDto(savedRequest);


    }

    private TransportationRequest mapToRequest(TransportationRequestResp transportationRequestDTO) {
        TransportationRequest transportationRequest = new TransportationRequest();

        transportationRequest.setLoadingPayload(loadingPayloadMapperDTO
                .toEntity(transportationRequestDTO.getLoadingPayload()));

        transportationRequest.setUnloadingPayload(unloadingPayloadMapperDTO
                .toEntity(transportationRequestDTO.getUnloadingPayload()));

        final String carrierUNP = transportationRequestDTO.getCarrier().getUNP();
        final String customerUNP = transportationRequestDTO.getCustomer().getUNP();

        if (!carrierRepository.existsCarrierByUNP(carrierUNP)) {

            transportationRequest.setCarrier(carrierMapperDTO.toEntity(transportationRequestDTO.getCarrier()));

        } else {
            transportationRequest.setCarrier(carrierRepository.findCarrierByUNP(carrierUNP).get());
        }

        if (!customerRepository.existsCustomerByUNP(customerUNP)) {
            transportationRequest.setCustomer(customerMapperDTO.toEntity(transportationRequestDTO.getCustomer()));
        } else {
            transportationRequest.setCustomer(customerRepository.findCustomerByUNP(customerUNP).get());
        }

        transportationRequest.setAdditionalInformation(transportationRequestDTO.getAdditionalInformation());
        transportationRequest.setStatus(transportationRequestDTO.getStatus());
        transportationRequest.setCargoOfferId(transportationRequestDTO.getCargoOfferId());
        transportationRequest.setTruckOfferId(transportationRequestDTO.getTruckOfferId());
        transportationRequest.setFreightCost(transportationRequestDTO.getFreightCost());

        return transportationRequest;
    }

}
