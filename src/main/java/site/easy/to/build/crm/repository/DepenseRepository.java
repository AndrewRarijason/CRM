package site.easy.to.build.crm.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import site.easy.to.build.crm.entity.Budget;
import site.easy.to.build.crm.entity.Depense;

@Repository
public interface DepenseRepository extends JpaRepository<Depense, Integer> {
   
    @Query("SELECT COALESCE(SUM(d.valeurDepense), 0) " +
       "FROM Depense d " +
       "LEFT JOIN d.lead l " +
       "LEFT JOIN d.ticket t " +
       "LEFT JOIN l.customer c " +
       "LEFT JOIN t.customer c2 " +
       "WHERE c.customerId = :customerId OR c2.customerId = :customerId")    
    public double getTotalDepenseByCustomerId(@Param("customerId") int customerId);
}