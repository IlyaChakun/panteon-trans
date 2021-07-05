package by.iba.exchange.truck.controler;

import by.iba.common.controller.ControllerHelper;
import by.iba.common.dto.PageWrapper;
import by.iba.exchange.truck.dto.TruckOfferReqResp;
import by.iba.exchange.truck.service.TruckOfferService;
import by.iba.exchange.truck.dto.TruckOfferResp;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;


@RestController
@AllArgsConstructor
@Slf4j
public class TruckOfferControllerImpl implements TruckOfferController {

    private final TruckOfferService truckOfferService;

    @Override
    public ResponseEntity<TruckOfferResp> save(TruckOfferReqResp truckDTO, final BindingResult bindingResult) {
        ControllerHelper.checkBindingResultAndThrowExceptionIfInvalid(bindingResult);

        final TruckOfferResp saved = truckOfferService.save(truckDTO);

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
    public ResponseEntity<TruckOfferResp> findById(String truckId) {

        final TruckOfferResp response = truckOfferService.findById(Long.valueOf(truckId));

        return ResponseEntity
                .ok()
                .body(response);
    }

    @Override
    public ResponseEntity<PageWrapper<TruckOfferResp>> findAll(@RequestParam(defaultValue = "0", required = false) Integer page,
                                                               @RequestParam(defaultValue = "10", required = false) Integer size) {

        final PageWrapper<TruckOfferResp> pageWrapper = truckOfferService.findAll(page, size);

        return ResponseEntity
                .ok()
                .body(pageWrapper);
    }
}
