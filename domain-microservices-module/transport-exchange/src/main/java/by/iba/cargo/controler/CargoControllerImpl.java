package by.iba.cargo.controler;

import by.iba.cargo.dto.CargoOfferDTO;
import by.iba.cargo.dto.CargoOfferReqDTO;
import by.iba.cargo.dto.CargoSearchCriteriaDTO;
import by.iba.cargo.service.CargoService;
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
public class CargoControllerImpl implements CargoController {

    private final CargoService cargoService;

    @Override
    public ResponseEntity<CargoOfferDTO> save(@Valid final CargoOfferReqDTO cargoOfferReqDTO,
                                              final BindingResult bindingResult) {

        ControllerHelper.checkBindingResultAndThrowExceptionIfInvalid(bindingResult);

        log.info("Received a request to save the cargo ");
         final CargoOfferDTO savedCargo = cargoService.save(cargoOfferReqDTO);

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
        log.info("Received a request to delete the cargo with id = {}", cargoId);

        cargoService.delete(Long.valueOf(cargoId));

        return ResponseEntity.ok().build();
    }

    @Override
    public ResponseEntity<CargoOfferDTO> findById(String cargoId) {
        log.info("Received a request to find the cargo by id = {}", cargoId);

        final CargoOfferDTO cargoOfferDTO = cargoService.findById(Long.valueOf(cargoId));

        return ResponseEntity
                .ok()
                .body(cargoOfferDTO);
    }

    @Override
    public ResponseEntity<PageWrapper<CargoOfferDTO>> findAll(final Integer page, final Integer size, CargoSearchCriteriaDTO cargoSearchCriteriaDTO) {
        log.info("Received a request to find all cargo");

        final PageWrapper<CargoOfferDTO> cargo = cargoService.findAll(page, size, cargoSearchCriteriaDTO);

        return ResponseEntity
                .ok()
                .body(cargo);
    }
}
