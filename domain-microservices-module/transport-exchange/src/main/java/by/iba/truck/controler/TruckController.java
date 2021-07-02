package by.iba.truck.controler;

import by.iba.common.dto.PageWrapper;
import by.iba.common.validation.annotation.PositiveLong;
import by.iba.truck.dto.TruckOfferDTO;
import by.iba.truck.dto.TruckOfferReqDTO;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RequestMapping("/trucks")
@CrossOrigin(origins = "*")
public interface TruckController {

    @PostMapping
    ResponseEntity<TruckOfferDTO> save(@Valid @RequestBody TruckOfferReqDTO truckDTO,
                                       final BindingResult bindingResult);

    @GetMapping("/{id}")
    ResponseEntity<TruckOfferDTO> findById(@PathVariable("id") @PositiveLong String truckId);

    @GetMapping
    ResponseEntity<PageWrapper<TruckOfferDTO>> findAll(@RequestParam(defaultValue = "0", required = false) Integer page,
                                                       @RequestParam(defaultValue = "10", required = false) Integer size);

}
