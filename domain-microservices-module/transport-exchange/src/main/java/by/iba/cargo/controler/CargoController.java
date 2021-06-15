package by.iba.cargo.controler;

import by.iba.cargo.dto.CargoDTO;
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
    ResponseEntity<CargoDTO> save(@Valid @RequestBody CargoDTO cargoDTO,
                                  final BindingResult bindingResult);

    @PutMapping("/{id}")
    ResponseEntity<CargoDTO> update(@PathVariable("id") @PositiveLong String cargoId,
                                    @RequestBody @Valid CargoDTO cargoDTO,
                                    BindingResult bindingResult);

    @DeleteMapping("/{id}")
    ResponseEntity<Void> delete(@PathVariable("id") @PositiveLong String cargoId);

    @GetMapping("/{id}")
    ResponseEntity<CargoDTO> findById(@PathVariable("id") @PositiveLong String cargoId);

    @GetMapping
    ResponseEntity<PageWrapper<CargoDTO>> findAll(@RequestParam(defaultValue = "1", required = false) final Integer page,
                                                  @RequestParam(defaultValue = "10", required = false) final Integer size);
}
