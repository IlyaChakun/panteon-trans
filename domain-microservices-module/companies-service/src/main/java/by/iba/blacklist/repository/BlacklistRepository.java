package by.iba.blacklist.repository;

import by.iba.blacklist.domain.Blacklist;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface BlacklistRepository extends JpaRepository<Blacklist, Long>, JpaSpecificationExecutor<Blacklist> {

    Optional<Blacklist> findBlacklistByCompanyId(Long companyId);

    Page<Blacklist> findAllByCompanyId(Long companyId, Pageable var1);


}