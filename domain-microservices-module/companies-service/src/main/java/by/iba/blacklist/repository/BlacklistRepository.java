package by.iba.blacklist.repository;

import by.iba.blacklist.domain.Blacklist;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

@Repository
public interface BlacklistRepository extends JpaRepository<Blacklist, Long>, JpaSpecificationExecutor<Blacklist> {
 
}
