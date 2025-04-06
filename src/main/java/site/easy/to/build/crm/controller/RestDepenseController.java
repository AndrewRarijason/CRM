package site.easy.to.build.crm.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import site.easy.to.build.crm.entity.Depense;
import site.easy.to.build.crm.service.depense.DepenseService;
import site.easy.to.build.crm.service.lead.LeadService;
import site.easy.to.build.crm.service.ticket.TicketService;

@RestController
@RequestMapping("/api/depenses")
public class RestDepenseController {
    
    private final DepenseService depenseService;
    private final LeadService leadService;
    private final TicketService ticketService;

    public RestDepenseController(DepenseService depenseService, LeadService leadService, TicketService ticketService) {
        this.depenseService = depenseService;
        this.leadService = leadService;
        this.ticketService = ticketService;
    }

    @GetMapping("/update/{id}/{valeur}")
    public ResponseEntity<Depense> updateDepense(@PathVariable Integer id, @PathVariable double valeur) {
        Depense depense = depenseService.getDepenseById(id).orElse(null);
        System.out.println("valeur"+valeur);
        System.out.println("id"+id);
        if (depense != null) {
            depense.setValeurDepense(valeur);
            depense = depenseService.saveDepense(depense);
            return ResponseEntity.ok(depense);
        } else {
            return ResponseEntity.notFound().build();
        }
    }


    @GetMapping("/delete/{id}")
    public ResponseEntity<Depense> deleteDepense(@PathVariable Integer id) {
        Depense depense = depenseService.getDepenseById(id).orElse(null);
        System.out.println("id"+id);
        if (depense != null) {
            depenseService.deleteDepense(id);
            if (depense.getLead()!=null) {
                leadService.delete(depense.getLead());
            }
            else{
                ticketService.delete(depense.getTicket());
            }
            return ResponseEntity.ok(depense);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    

}
