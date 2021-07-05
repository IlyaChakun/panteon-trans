//package by.iba.exchange.request.controller;
//
//import by.iba.common.dto.PageWrapper;
//import by.iba.exchange.request.dto.TransportationRequestResp;
//import org.springframework.http.ResponseEntity;
//import org.springframework.validation.BindingResult;
//import org.springframework.web.bind.annotation.*;
//
//import javax.validation.Valid;
//
//@RequestMapping("/transportation-offers")
//public interface TransportationRequestController {
//
//    @PostMapping()
//    ResponseEntity<TransportationRequestResp> save(@Valid @RequestBody TransportationRequestResp transportationRequestDTO,
//                                                   final BindingResult bindingResult);
//
//    @GetMapping("/{id}")
//    ResponseEntity<TransportationRequestResp> findById(@PathVariable("id") final Long id);
//
//    @GetMapping
//    ResponseEntity<PageWrapper<TransportationRequestResp>> findAll(@RequestParam(defaultValue = "0", required = false) final Integer page,
//                                                                   @RequestParam(defaultValue = "10", required = false) final Integer size);
//
//    @GetMapping("/by-carrier/{carrierId}")
//    ResponseEntity<PageWrapper<TransportationRequestResp>> findAllByCarrierId(@PathVariable("carrierId") final Long carrierId,
//                                                                              @RequestParam(defaultValue = "0", required = false) final Integer page,
//                                                                              @RequestParam(defaultValue = "10", required = false) final Integer size);
//
//    @GetMapping("/by-customer/{customerId}")
//    ResponseEntity<PageWrapper<TransportationRequestResp>> findAllByCustomerId(@PathVariable("customerId") final Long customerId,
//                                                                               @RequestParam(defaultValue = "0", required = false) final Integer page,
//                                                                               @RequestParam(defaultValue = "10", required = false) final Integer size);
//
//}
