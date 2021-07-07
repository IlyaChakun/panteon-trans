package by.iba.exchange.request.service;
import by.iba.common.dto.PageWrapper;
import by.iba.common.dto.PatchReq;
import by.iba.exchange.request.dto.TransportationRequestResp;
import com.github.fge.jsonpatch.JsonPatch;

import java.util.Map;

public interface TransportationRequestService {

    TransportationRequestResp save(TransportationRequestResp transportationRequestDTO);

    TransportationRequestResp findById(Long id);

    PageWrapper<TransportationRequestResp> findAllByCarrierId(final Integer page,
                                                              final Integer size,
                                                              final Long carrierId);

    PageWrapper<TransportationRequestResp> findAllByCustomerId(final Integer page,
                                                               final Integer size,
                                                               final Long customerId);

    PageWrapper<TransportationRequestResp> findAll(final Integer page, final Integer size);

    TransportationRequestResp partialUpdate(PatchReq patch, Long id);

}
