package by.iba.service.core;

import java.util.List;

public interface BaseSearchService<T> {

    List<T> findAll();

    T getOne(final Long id);

    Boolean existById(final Long id);

}
