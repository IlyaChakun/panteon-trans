//package by.iba.exchange.request.controller;
//
//import by.iba.common.controller.ControllerHelper;
//import by.iba.common.dto.PageWrapper;
//import by.iba.exchange.request.dto.TransportationRequestResp;
//import by.iba.exchange.request.service.TransportationRequestService;
//import lombok.AllArgsConstructor;
//import lombok.extern.slf4j.Slf4j;
//import org.springframework.http.ResponseEntity;
//import org.springframework.validation.BindingResult;
//import org.springframework.web.bind.annotation.RestController;
//import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
//
//import java.net.URI;
//
//@RestController
//@AllArgsConstructor
//@Slf4j
//public class TransportationRequestControllerImpl implements TransportationRequestController {
//
//    private final TransportationRequestService requestService;
//
//    @Override
//    public ResponseEntity<TransportationRequestResp> save(TransportationRequestResp transportationRequestDTO,
//                                                          BindingResult bindingResult) {
//
//        ControllerHelper.checkBindingResultAndThrowExceptionIfInvalid(bindingResult);
//
//        final TransportationRequestResp saved = requestService.save(transportationRequestDTO);
//
//        final URI location = ServletUriComponentsBuilder
//                .fromCurrentContextPath()
//                .path("/trucks/{id}")
//                .buildAndExpand(saved.getId())
//                .toUri();
//
//        return ResponseEntity
//                .created(location)
//                .body(saved);
//    }
//
//    @Override
//    public ResponseEntity<TransportationRequestResp> findById(Long id) {
//
//        final TransportationRequestResp response = requestService.findById(id);
//
//        return ResponseEntity
//                .ok()
//                .body(response);
//    }
//
//    @Override
//    public ResponseEntity<PageWrapper<TransportationRequestResp>> findAll(Integer page, Integer size) {
//        final PageWrapper<TransportationRequestResp> pageWrapper = requestService.findAll(page, size);
//
//
//        return ResponseEntity
//                .ok()
//                .body(pageWrapper);
//    }
//
//    @Override
//    public ResponseEntity<PageWrapper<TransportationRequestResp>> findAllByCarrierId(Long carrierId,
//                                                                                     Integer page,
//                                                                                     Integer size) {
//
//        final PageWrapper<TransportationRequestResp> pageWrapper = requestService.findAllByCarrierId(page, size, carrierId);
//
//
//        return ResponseEntity
//                .ok()
//                .body(pageWrapper);
//    }
//
//    @Override
//    public ResponseEntity<PageWrapper<TransportationRequestResp>> findAllByCustomerId(Long customerId,
//                                                                                      Integer page,
//                                                                                      Integer size) {
//
//
//        final PageWrapper<TransportationRequestResp> pageWrapper = requestService.findAllByCustomerId(page, size, customerId);
//
//
//        return ResponseEntity
//                .ok()
//                .body(pageWrapper);
//    }
//}
