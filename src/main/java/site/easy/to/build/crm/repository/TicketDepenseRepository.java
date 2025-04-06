package site.easy.to.build.crm.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import site.easy.to.build.crm.entity.TicketDepenseDetails;

@Repository
public interface TicketDepenseRepository extends JpaRepository<TicketDepenseDetails, Long> {
    List<TicketDepenseDetails> findByCustomerId(Long customerId);
}
