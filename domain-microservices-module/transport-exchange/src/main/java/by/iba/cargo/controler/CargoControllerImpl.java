package by.iba.cargo.controler;

import by.iba.cargo.dto.CargoDTO;
import by.iba.cargo.dto.CargoReqDTO;
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
    public ResponseEntity<CargoDTO> save(@Valid final CargoReqDTO cargoReqDTO,
                                         final BindingResult bindingResult) {

        ControllerHelper.checkBindingResultAndThrowExceptionIfInvalid(bindingResult);

        log.info("Received a request to save the cargo ");

        final CargoDTO savedCargo = cargoService.save(cargoReqDTO);

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
    public ResponseEntity<CargoDTO> update(String cargoId, @Valid CargoReqDTO cargoReqDTO, BindingResult bindingResult) {
        log.info("Received a request to update the cargo with id = {}", cargoId);

        final CargoDTO updatedCargo = cargoService.update(Long.valueOf(cargoId), cargoReqDTO);

        return ResponseEntity
                .ok()
                .body(updatedCargo);
    }


    @Override
    public ResponseEntity<Void> delete(final String cargoId) {
        log.info("Received a request to delete the cargo with id = {}", cargoId);

        cargoService.delete(Long.valueOf(cargoId));

        return ResponseEntity.ok().build();
    }

    @Override
    public ResponseEntity<CargoDTO> findById(String cargoId) {
        log.info("Received a request to find the cargo by id = {}", cargoId);

        final CargoDTO cargoDTO = cargoService.findById(Long.valueOf(cargoId));

        return ResponseEntity
                .ok()
                .body(cargoDTO);
    }

    @Override
    public ResponseEntity<PageWrapper<CargoDTO>> findAll(final Integer page, final Integer size, CargoSearchCriteriaDTO cargoSearchCriteriaDTO ) {
        log.info("Received a request to find all cargo");

        final PageWrapper<CargoDTO> cargo = cargoService.findAll(page, size, cargoSearchCriteriaDTO);

        return ResponseEntity
                .ok()
                .body(cargo);
    }
}
