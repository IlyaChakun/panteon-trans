package by.iba.exchange.truck.controler;

import by.iba.common.controller.ControllerHelper;
import by.iba.common.dto.PageWrapper;
import by.iba.exchange.truck.dto.TruckOfferReqDTO;
import by.iba.exchange.truck.service.TruckService;
import by.iba.exchange.truck.dto.TruckOfferDTO;
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
public class TruckControllerImpl implements TruckController {

    private final TruckService truckService;

    @Override
    public ResponseEntity<TruckOfferDTO> save(TruckOfferReqDTO truckDTO, final BindingResult bindingResult) {
        ControllerHelper.checkBindingResultAndThrowExceptionIfInvalid(bindingResult);

        final TruckOfferDTO saved = truckService.save(truckDTO);

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
    public ResponseEntity<TruckOfferDTO> findById(String truckId) {

        final TruckOfferDTO response = truckService.findById(Long.valueOf(truckId));

        return ResponseEntity
                .ok()
                .body(response);
    }

    @Override
    public ResponseEntity<PageWrapper<TruckOfferDTO>> findAll(@RequestParam(defaultValue = "0", required = false) Integer page,
                                                              @RequestParam(defaultValue = "10", required = false) Integer size) {

        final PageWrapper<TruckOfferDTO> pageWrapper = truckService.findAll(page, size);

        return ResponseEntity
                .ok()
                .body(pageWrapper);
    }
}
