package site.easy.to.build.crm.entity;

import site.easy.to.build.crm.repository.TicketRepository;
import site.easy.to.build.crm.service.depense.DepenseService;
import site.easy.to.build.crm.service.ticket.TicketService;
import site.easy.to.build.crm.service.ticket.TicketServiceImpl;

public class TicketDepense {
    Ticket ticket;
    Depense depense;
    public TicketDepense(Ticket ticket, Depense depense) {
        this.ticket = ticket;
        this.depense = depense;
    }
    public Ticket getTicket() {
        return ticket;
    }
    public void setTicket(Ticket ticket) {
        this.ticket = ticket;
    }
    public Depense getDepense() {
        return depense;
    }
    public void setDepense(Depense depense) {
        this.depense = depense;
    }
    
}
