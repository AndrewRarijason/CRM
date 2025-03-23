package site.easy.to.build.crm.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.multipart.MultipartFile;

import site.easy.to.build.crm.entity.TicketDepense;
import site.easy.to.build.crm.service.importation.ImportTicket;
import site.easy.to.build.crm.service.customer.CustomerService;
import site.easy.to.build.crm.service.depense.DepenseService;
import site.easy.to.build.crm.service.ticket.TicketService;
import site.easy.to.build.crm.service.user.UserService;


@Controller
@RequestMapping("/import")
public class ImportCsvController {

    private DepenseService depenseService;
    private TicketService ticketService;
    private UserService userService;
    private CustomerService customerService;
    
    @Autowired
    public ImportCsvController(DepenseService depenseService, TicketService ticketService, UserService userService,
            CustomerService customerService) {
        this.depenseService = depenseService;
        this.ticketService = ticketService;
        this.userService = userService;
        this.customerService = customerService;
    }

    @GetMapping("/pageTicketDepense")
    public String importCsv() {
        return "eval/import";
    }

    @PostMapping("/importTicketDepense")
    public String importCsv(MultipartFile file,Model model) {
        ImportTicket importTicket = new ImportTicket();
        try {
            List<TicketDepense> ticketDepense=importTicket.importTicket(file, ',', depenseService, ticketService, userService, customerService);
            model.addAttribute("ticketDepense", ticketDepense);
            importTicket.insertCSV(ticketDepense, depenseService, ticketService);
        } catch (Exception e) {
            model.addAttribute("errorMessage", e.getMessage());
        }
        return "eval/import";
    }
    
    

}
