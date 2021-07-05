package by.iba.exchange.request.controller;

import by.iba.common.controller.ControllerHelper;
import by.iba.common.dto.PageWrapper;
import by.iba.exchange.request.dto.TransportationRequestDTO;
import by.iba.exchange.request.service.TransportationRequestService;
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
public class TransportationRequestControllerImpl implements TransportationRequestController {

    private final TransportationRequestService requestService;

    @Override
    public ResponseEntity<TransportationRequestDTO> save(TransportationRequestDTO transportationRequestDTO,
                                                         BindingResult bindingResult) {

        ControllerHelper.checkBindingResultAndThrowExceptionIfInvalid(bindingResult);

        final TransportationRequestDTO saved = requestService.save(transportationRequestDTO);

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
    public ResponseEntity<TransportationRequestDTO> findById(Long id) {

        final TransportationRequestDTO response = requestService.findById(id);

        return ResponseEntity
                .ok()
                .body(response);
    }

    @Override
    public ResponseEntity<PageWrapper<TransportationRequestDTO>> findAll(Integer page, Integer size) {
        final PageWrapper<TransportationRequestDTO> pageWrapper = requestService.findAll(page, size);


        return ResponseEntity
                .ok()
                .body(pageWrapper);
    }

    @Override
    public ResponseEntity<PageWrapper<TransportationRequestDTO>> findAllByCarrierId(Long carrierId,
                                                                                    Integer page,
                                                                                    Integer size) {

        final PageWrapper<TransportationRequestDTO> pageWrapper = requestService.findAllByCarrierId(page, size, carrierId);


        return ResponseEntity
                .ok()
                .body(pageWrapper);
    }

    @Override
    public ResponseEntity<PageWrapper<TransportationRequestDTO>> findAllByCustomerId(Long customerId,
                                                                                     Integer page,
                                                                                     Integer size) {


        final PageWrapper<TransportationRequestDTO> pageWrapper = requestService.findAllByCustomerId(page, size, customerId);


        return ResponseEntity
                .ok()
                .body(pageWrapper);
    }
}
