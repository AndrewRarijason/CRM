package site.easy.to.build.crm.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import site.easy.to.build.crm.entity.TicketDepenseDetails;
import site.easy.to.build.crm.service.details.TicketDepenseService;

import java.util.List;

@RestController
@RequestMapping("/api/tickets-depenses")
public class TicketDepenseController {

    private final TicketDepenseService ticketDepenseService;

    public TicketDepenseController(TicketDepenseService ticketDepenseService) {
        this.ticketDepenseService = ticketDepenseService;
    }

    /**
     * Récupérer tous les tickets avec dépenses
     */
    @GetMapping
    public ResponseEntity<List<TicketDepenseDetails>> getAllTicketsDepenses() {
        List<TicketDepenseDetails> tickets = ticketDepenseService.getAllTicketDepenses();
        return ResponseEntity.ok(tickets); // 200 OK
    }

    @GetMapping("/customer/{customerId}")
    public ResponseEntity<List<TicketDepenseDetails>> getTicketDepensesByCustomerId(@PathVariable Long customerId) {
        List<TicketDepenseDetails> tickets = ticketDepenseService.getTicketDepensesByCustomerId(customerId);
        return ResponseEntity.ok(tickets); // 200 OK
    }

 
}