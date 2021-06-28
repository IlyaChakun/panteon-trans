package by.iba.cargo.controler;

import by.iba.cargo.dto.CargoDTO;
import by.iba.cargo.dto.CargoReqDTO;
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
    ResponseEntity<CargoDTO> save(@Valid @RequestBody CargoReqDTO cargoReqDTO,
                                  final BindingResult bindingResult);

    @PutMapping("/{id}")
    ResponseEntity<CargoDTO> update(@PathVariable("id") @PositiveLong String cargoId,
                                    @RequestBody @Valid CargoReqDTO cargoReqDTO,
                                    BindingResult bindingResult);

    @DeleteMapping("/{id}")
    ResponseEntity<Void> delete(@PathVariable("id") @PositiveLong String cargoId);

    @GetMapping("/{id}")
    ResponseEntity<CargoDTO> findById(@PathVariable("id") @PositiveLong String cargoId);

    @GetMapping()
    ResponseEntity<PageWrapper<CargoDTO>> findAll(@RequestParam(defaultValue = "0", required = false) Integer page,
                                                  @RequestParam(defaultValue = "10", required = false) Integer size,
                                                  @Valid @RequestBody CargoSearchCriteriaDTO cargoSearchCriteriaDTO);
}
