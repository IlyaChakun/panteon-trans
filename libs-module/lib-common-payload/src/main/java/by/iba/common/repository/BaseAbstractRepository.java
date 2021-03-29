package by.iba.common.repository;


import by.iba.common.domain.BaseAbstractEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.NoRepositoryBean;

@NoRepositoryBean
public interface BaseAbstractRepository<T extends BaseAbstractEntity>
        extends JpaRepository<T, Long> {

}