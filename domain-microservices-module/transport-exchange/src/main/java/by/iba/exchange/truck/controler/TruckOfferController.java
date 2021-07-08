package by.iba.exchange.truck.controler;

import by.iba.common.dto.PageWrapper;
import by.iba.common.dto.PatchReq;
import by.iba.common.validation.annotation.PositiveLong;
import by.iba.exchange.truck.dto.TruckOfferReq;
import by.iba.exchange.truck.dto.TruckOfferResp;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RequestMapping("/trucks")
@CrossOrigin(origins = "*")
public interface TruckOfferController {

    @PostMapping
    ResponseEntity<TruckOfferResp> save(@Valid @RequestBody TruckOfferReq truckDTO,
                                        final BindingResult bindingResult);

    @GetMapping("/{id}")
    ResponseEntity<TruckOfferResp> findById(@PathVariable("id") @PositiveLong String truckId);

    @GetMapping
    ResponseEntity<PageWrapper<TruckOfferResp>> findAll(@RequestParam(defaultValue = "0", required = false) Integer page,
                                                        @RequestParam(defaultValue = "10", required = false) Integer size);

    @PatchMapping("/{id}")
    ResponseEntity<TruckOfferResp> patch(@RequestBody PatchReq patch,
                                         @PathVariable Long id);
}
