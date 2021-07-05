package by.iba.exchange.request.service;
import by.iba.common.dto.PageWrapper;
import by.iba.exchange.request.dto.TransportationRequestDTO;

public interface TransportationRequestService {

    TransportationRequestDTO save(TransportationRequestDTO transportationRequestDTO);

    TransportationRequestDTO findById(Long id);

    PageWrapper<TransportationRequestDTO> findAllByCarrierId(final Integer page,
                                                             final Integer size,
                                                             final Long carrierId);

    PageWrapper<TransportationRequestDTO> findAllByCustomerId(final Integer page,
                                                              final Integer size,
                                                              final Long customerId);

    PageWrapper<TransportationRequestDTO> findAll(final Integer page, final Integer size);

}
