package by.iba.exchange.request.controller;

import by.iba.common.dto.PageWrapper;
import by.iba.common.dto.PatchReq;
import by.iba.exchange.request.dto.TransportationReqResp;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RequestMapping("/transportation-offers")
public interface TransportationReqController {

    @PostMapping()
    ResponseEntity<TransportationReqResp> save(@Valid @RequestBody TransportationReqResp transportationRequestDTO,
                                               final BindingResult bindingResult);

    @GetMapping("/{id}")
    ResponseEntity<TransportationReqResp> findById(@PathVariable("id") final Long id);

    @GetMapping
    ResponseEntity<PageWrapper<TransportationReqResp>> findAll(@RequestParam(defaultValue = "0", required = false) final Integer page,
                                                               @RequestParam(defaultValue = "10", required = false) final Integer size);

    @GetMapping("/by-carrier/{carrierId}")
    ResponseEntity<PageWrapper<TransportationReqResp>> findAllByCarrierId(@PathVariable("carrierId") final Long carrierId,
                                                                          @RequestParam(defaultValue = "0", required = false) final Integer page,
                                                                          @RequestParam(defaultValue = "10", required = false) final Integer size);

    @GetMapping("/by-customer/{customerId}")
    ResponseEntity<PageWrapper<TransportationReqResp>> findAllByCustomerId(@PathVariable("customerId") final Long customerId,
                                                                           @RequestParam(defaultValue = "0", required = false) final Integer page,
                                                                           @RequestParam(defaultValue = "10", required = false) final Integer size);

    @PatchMapping("/{id}")
    ResponseEntity<TransportationReqResp>patch(@RequestBody PatchReq patch,
                                               @PathVariable Long id);
}
