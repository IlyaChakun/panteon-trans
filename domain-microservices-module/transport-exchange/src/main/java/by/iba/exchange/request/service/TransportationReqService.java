package by.iba.exchange.request.service;
import by.iba.common.dto.PageWrapper;
import by.iba.common.dto.PatchReq;
import by.iba.exchange.request.dto.TransportationReqResp;

public interface TransportationReqService {

    TransportationReqResp save(TransportationReqResp transportationRequestDTO);

    TransportationReqResp findById(Long id);

    PageWrapper<TransportationReqResp> findAllByCarrierId(final Integer page,
                                                          final Integer size,
                                                          final Long carrierId);

    PageWrapper<TransportationReqResp> findAllByCustomerId(final Integer page,
                                                           final Integer size,
                                                           final Long customerId);

    PageWrapper<TransportationReqResp> findAll(final Integer page, final Integer size);

    TransportationReqResp partialUpdate(PatchReq patch, Long id);

}
