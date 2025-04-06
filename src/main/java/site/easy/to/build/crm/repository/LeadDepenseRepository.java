package site.easy.to.build.crm.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import site.easy.to.build.crm.entity.LeadDepenseDetails;

@Repository
public interface LeadDepenseRepository extends JpaRepository<LeadDepenseDetails, Long> {
    List<LeadDepenseDetails> findByCustomerId(Long customerId);
}
