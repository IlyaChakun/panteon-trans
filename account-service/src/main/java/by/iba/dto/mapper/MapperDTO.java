package by.iba.dto.mapper;


import by.iba.domain.AbstractEntity;
import by.iba.dto.AbstractDTO;

import java.util.List;

public interface MapperDTO<E extends AbstractEntity, D extends AbstractDTO> {

    E toEntity(final D d);

    D toDto(final E e);

    List<D> toDtoList(final List<E> eList);
}