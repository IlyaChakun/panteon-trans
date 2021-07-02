package by.iba.request.controller;

import by.iba.common.dto.PageWrapper;
import by.iba.request.dto.TransportationRequestDTO;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RequestMapping("/transportation-offers")
public interface TransportationRequestController {

    @PostMapping()
    ResponseEntity<TransportationRequestDTO> save(@Valid @RequestBody TransportationRequestDTO transportationRequestDTO,
                                                  final BindingResult bindingResult);

    @GetMapping("/{id}")
    ResponseEntity<TransportationRequestDTO> findById(@PathVariable("id") final Long id);

    @GetMapping
    ResponseEntity<PageWrapper<TransportationRequestDTO>> findAll(@RequestParam(defaultValue = "0", required = false) final Integer page,
                                                                  @RequestParam(defaultValue = "10", required = false) final Integer size);

    @GetMapping("/by-carrier/{carrierId}")
    ResponseEntity<PageWrapper<TransportationRequestDTO>> findAllByCarrierId(@PathVariable("carrierId") final Long carrierId,
                                                                             @RequestParam(defaultValue = "0", required = false) final Integer page,
                                                                             @RequestParam(defaultValue = "10", required = false) final Integer size);

    @GetMapping("/by-customer/{customerId}")
    ResponseEntity<PageWrapper<TransportationRequestDTO>> findAllByCustomerId(@PathVariable("customerId") final Long customerId,
                                                                 @RequestParam(defaultValue = "0", required = false) final Integer page,
                                                                 @RequestParam(defaultValue = "10", required = false) final Integer size);

}
