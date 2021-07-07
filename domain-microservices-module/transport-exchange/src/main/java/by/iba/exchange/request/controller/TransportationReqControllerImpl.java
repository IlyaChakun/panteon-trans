package by.iba.exchange.request.controller;

import by.iba.common.controller.ControllerHelper;
import by.iba.common.dto.PageWrapper;
import by.iba.common.dto.PatchReq;
import by.iba.exchange.request.dto.TransportationReqResp;
import by.iba.exchange.request.service.TransportationReqService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;

@RestController
@AllArgsConstructor
@Slf4j
public class TransportationReqControllerImpl implements TransportationReqController {

    private final TransportationReqService requestService;

    @Override
    public ResponseEntity<TransportationReqResp> save(TransportationReqResp transportationRequestDTO,
                                                      BindingResult bindingResult) {

        ControllerHelper.checkBindingResultAndThrowExceptionIfInvalid(bindingResult);

        final TransportationReqResp saved = requestService.save(transportationRequestDTO);

        final URI location = ServletUriComponentsBuilder
                .fromCurrentContextPath()
                .path("/trucks/{id}")
                .buildAndExpand(saved.getId())
                .toUri();

        return ResponseEntity
                .created(location)
                .body(saved);
    }

    @Override
    public ResponseEntity<TransportationReqResp> findById(Long id) {

        final TransportationReqResp response = requestService.findById(id);

        return ResponseEntity
                .ok()
                .body(response);
    }

    @Override
    public ResponseEntity<PageWrapper<TransportationReqResp>> findAll(Integer page, Integer size) {
        final PageWrapper<TransportationReqResp> pageWrapper = requestService.findAll(page, size);


        return ResponseEntity
                .ok()
                .body(pageWrapper);
    }

    @Override
    public ResponseEntity<PageWrapper<TransportationReqResp>> findAllByCarrierId(Long carrierId,
                                                                                 Integer page,
                                                                                 Integer size) {

        final PageWrapper<TransportationReqResp> pageWrapper = requestService.findAllByCarrierId(page, size, carrierId);


        return ResponseEntity
                .ok()
                .body(pageWrapper);
    }

    @Override
    public ResponseEntity<PageWrapper<TransportationReqResp>> findAllByCustomerId(Long customerId,
                                                                                  Integer page,
                                                                                  Integer size) {


        final PageWrapper<TransportationReqResp> pageWrapper = requestService.findAllByCustomerId(page, size, customerId);


        return ResponseEntity
                .ok()
                .body(pageWrapper);
    }

    @Override
    public ResponseEntity<TransportationReqResp> patch(PatchReq patch, Long id) {
        log.info("Received request to update transportation request with id = {}", id);

        TransportationReqResp updated = requestService.partialUpdate(patch, id);

        return ResponseEntity
                .ok()
                .body(updated);
    }

}
