package site.easy.to.build.crm.repository;

import site.easy.to.build.crm.entity.DepenseStat;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.Optional;

@Repository
public interface DepenseStatRepository extends JpaRepository<DepenseStat, Long> {
    Optional<DepenseStat> findFirstBy();
}
