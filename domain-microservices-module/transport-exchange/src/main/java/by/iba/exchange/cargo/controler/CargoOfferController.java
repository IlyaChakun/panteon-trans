package by.iba.exchange.cargo.controler;

import by.iba.exchange.cargo.dto.CargoOfferResp;
import by.iba.exchange.cargo.dto.CargoOfferReqResp;
import by.iba.exchange.cargo.dto.CargoSearchCriteriaDTO;
import by.iba.common.dto.PageWrapper;
import by.iba.common.validation.annotation.PositiveLong;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RequestMapping("/cargo")
@CrossOrigin(origins = "*")
public interface CargoOfferController {

    @PostMapping
    ResponseEntity<CargoOfferResp> save(@Valid @RequestBody CargoOfferReqResp cargoOfferReqDTO,
                                        final BindingResult bindingResult);

    @DeleteMapping("/{id}")
    ResponseEntity<Void> delete(@PathVariable("id") @PositiveLong String cargoId);

    @GetMapping("/{id}")
    ResponseEntity<CargoOfferResp> findById(@PathVariable("id") @PositiveLong String cargoId);

    @GetMapping()
    ResponseEntity<PageWrapper<CargoOfferResp>> findAll(@RequestParam(defaultValue = "0", required = false) Integer page,
                                                        @RequestParam(defaultValue = "10", required = false) Integer size,
                                                        @Valid @RequestBody CargoSearchCriteriaDTO cargoSearchCriteriaDTO);
}
