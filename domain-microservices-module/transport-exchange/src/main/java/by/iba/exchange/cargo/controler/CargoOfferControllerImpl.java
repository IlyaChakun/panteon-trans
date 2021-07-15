package by.iba.exchange.cargo.controler;

import by.iba.common.dto.PatchReq;
import by.iba.exchange.cargo.dto.CargoOfferResp;
import by.iba.exchange.cargo.dto.CargoOfferReq;
import by.iba.exchange.cargo.dto.CargoSearchCriteriaDTO;
import by.iba.exchange.cargo.service.CargoOfferService;
import by.iba.common.controller.ControllerHelper;
import by.iba.common.dto.PageWrapper;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.validation.Valid;
import java.net.URI;


@RestController
@AllArgsConstructor
@Slf4j
public class CargoOfferControllerImpl implements CargoOfferController {

    private final CargoOfferService cargoOfferService;

    @Override
    public ResponseEntity<CargoOfferResp> save(@Valid final CargoOfferReq cargoOfferReqDTO,
                                               final BindingResult bindingResult) {

        ControllerHelper.checkBindingResultAndThrowExceptionIfInvalid(bindingResult);

        
         final CargoOfferResp savedCargo = cargoOfferService.save(cargoOfferReqDTO);

        final URI location = ServletUriComponentsBuilder
                .fromCurrentContextPath()
                .path("/cargo/{id}")
                .buildAndExpand(savedCargo.getId())
                .toUri();

        return ResponseEntity
                .created(location)
                .body(savedCargo);
    }

    @Override
    public ResponseEntity<Void> delete(final String cargoId) {
        

        cargoOfferService.delete(Long.valueOf(cargoId));

        return ResponseEntity.ok().build();
    }

    @Override
    public ResponseEntity<CargoOfferResp> findById(String cargoId) {
        

        final CargoOfferResp cargoOfferDTO = cargoOfferService.findById(Long.valueOf(cargoId));

        return ResponseEntity
                .ok()
                .body(cargoOfferDTO);
    }

    @Override
    public ResponseEntity<PageWrapper<CargoOfferResp>> findAll(final Integer page, final Integer size, CargoSearchCriteriaDTO cargoSearchCriteriaDTO) {
        

        final PageWrapper<CargoOfferResp> cargo = cargoOfferService.findAll(page, size, cargoSearchCriteriaDTO);

        return ResponseEntity
                .ok()
                .body(cargo);
    }

    @Override
    public ResponseEntity<CargoOfferResp> patch(PatchReq patch, Long id) {
        

        CargoOfferResp cargoOfferResp = cargoOfferService.partialUpdate(patch,id);

        return ResponseEntity
                .ok()
                .body(cargoOfferResp);
    }
}
