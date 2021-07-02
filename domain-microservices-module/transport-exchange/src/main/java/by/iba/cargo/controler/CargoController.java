package by.iba.cargo.controler;

import by.iba.cargo.dto.CargoOfferDTO;
import by.iba.cargo.dto.CargoOfferReqDTO;
import by.iba.cargo.dto.CargoSearchCriteriaDTO;
import by.iba.common.dto.PageWrapper;
import by.iba.common.validation.annotation.PositiveLong;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RequestMapping("/cargo")
@CrossOrigin(origins = "*")
public interface CargoController {

    @PostMapping
    ResponseEntity<CargoOfferDTO> save(@Valid @RequestBody CargoOfferReqDTO cargoOfferReqDTO,
                                       final BindingResult bindingResult);

    @DeleteMapping("/{id}")
    ResponseEntity<Void> delete(@PathVariable("id") @PositiveLong String cargoId);

    @GetMapping("/{id}")
    ResponseEntity<CargoOfferDTO> findById(@PathVariable("id") @PositiveLong String cargoId);

    @GetMapping()
    ResponseEntity<PageWrapper<CargoOfferDTO>> findAll(@RequestParam(defaultValue = "0", required = false) Integer page,
                                                       @RequestParam(defaultValue = "10", required = false) Integer size,
                                                       @Valid @RequestBody CargoSearchCriteriaDTO cargoSearchCriteriaDTO);
}
