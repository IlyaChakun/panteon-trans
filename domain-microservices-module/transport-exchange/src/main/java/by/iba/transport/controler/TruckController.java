package by.iba.transport.controler;

import by.iba.common.dto.PageWrapper;
import by.iba.common.validation.annotation.PositiveLong;
import by.iba.transport.dto.TruckDTO;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RequestMapping("/trucks")
@CrossOrigin(origins = "*")
public interface TruckController {

    @PostMapping
    ResponseEntity<TruckDTO> save(@Valid @RequestBody TruckDTO truckDTO);

    @PutMapping("/{id}")
    ResponseEntity<TruckDTO> update(@PathVariable("id") @PositiveLong String truckId,
                                    @RequestBody @Valid TruckDTO truckDTO,
                                    BindingResult bindingResult);

    @DeleteMapping("/{id}")
    ResponseEntity<Void> delete(@PathVariable("id") @PositiveLong String truckId);

    @GetMapping("/{id}")
    ResponseEntity<TruckDTO> findById(@PathVariable("id") @PositiveLong String truckId);

    @GetMapping
    ResponseEntity<PageWrapper<TruckDTO>> findAll();

}
