package site.easy.to.build.crm.repository;

import site.easy.to.build.crm.entity.StatistiqueBudget;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface StatistiqueBudgetRepository extends JpaRepository<StatistiqueBudget, Long> {
}
