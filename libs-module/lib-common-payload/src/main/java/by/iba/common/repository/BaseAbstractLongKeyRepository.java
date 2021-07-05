package by.iba.common.repository;


import by.iba.common.domain.core.BaseAbstractEntity;
import org.springframework.data.repository.NoRepositoryBean;

@NoRepositoryBean
public interface BaseAbstractLongKeyRepository<T extends BaseAbstractEntity>
        extends BaseAbstractRepository<T, Long> {
}