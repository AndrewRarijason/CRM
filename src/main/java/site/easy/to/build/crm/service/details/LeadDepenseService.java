package site.easy.to.build.crm.service.details;

import org.springframework.stereotype.Service;

import site.easy.to.build.crm.entity.LeadDepenseDetails;
import site.easy.to.build.crm.repository.LeadDepenseRepository;

import java.util.List;

@Service
public class LeadDepenseService {

    private final LeadDepenseRepository leadDepenseRepository;

    public LeadDepenseService(LeadDepenseRepository leadDepenseRepository) {
        this.leadDepenseRepository = leadDepenseRepository;
    }

    public List<LeadDepenseDetails> getAllLeadDepenses() {
        return leadDepenseRepository.findAll();
    }

    public List<LeadDepenseDetails> getLeadDepensesByCustomerId(Long customerId) {
        return leadDepenseRepository.findByCustomerId(customerId);
    }
}
