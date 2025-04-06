package site.easy.to.build.crm.service.details;

import org.springframework.stereotype.Service;

import site.easy.to.build.crm.entity.TicketDepenseDetails;
import site.easy.to.build.crm.repository.TicketDepenseRepository;

import java.util.List;

@Service
public class TicketDepenseService {

    private final TicketDepenseRepository ticketDepenseRepository;

    public TicketDepenseService(TicketDepenseRepository ticketDepenseRepository) {
        this.ticketDepenseRepository = ticketDepenseRepository;
    }

    public List<TicketDepenseDetails> getAllTicketDepenses() {
        return ticketDepenseRepository.findAll();
    }

    public List<TicketDepenseDetails> getTicketDepensesByCustomerId(Long customerId) {
        return ticketDepenseRepository.findByCustomerId(customerId);
    }

}
