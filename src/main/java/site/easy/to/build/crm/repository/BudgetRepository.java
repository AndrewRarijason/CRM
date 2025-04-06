package site.easy.to.build.crm.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import site.easy.to.build.crm.entity.Budget;
import site.easy.to.build.crm.entity.Lead;

import java.util.List;

@Repository
public interface BudgetRepository extends JpaRepository<Budget,Integer> {
    public List<Budget> findByCustomerCustomerId(int customerId);

    @Query("SELECT COALESCE(SUM(b.valeur), 0) FROM Budget b WHERE b.customer.customerId = :customerId")
    double getTotalBudgetByCustomerId(@Param("customerId") int customerId);
}
