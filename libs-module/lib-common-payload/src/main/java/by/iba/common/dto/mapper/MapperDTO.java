package by.iba.common.dto.mapper;


import by.iba.common.domain.BaseEntity;
import by.iba.common.dto.BaseDTO;

import java.util.List;

public interface MapperDTO<E extends BaseEntity, D extends BaseDTO> {

    E toEntity(final D d);

    D toDto(final E e);

    List<D> toDtoList(final List<E> eList);
}