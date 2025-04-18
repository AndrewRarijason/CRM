package site.easy.to.build.crm.controller;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import site.easy.to.build.crm.service.budget.BudgetService;
import site.easy.to.build.crm.service.customer.CustomerLoginInfoService;
import site.easy.to.build.crm.service.customer.CustomerService;
import site.easy.to.build.crm.service.depense.DepenseService;
import site.easy.to.build.crm.service.importcsv.CustomerRequestImportService;
import site.easy.to.build.crm.service.importcsv.ImportService;
import site.easy.to.build.crm.service.lead.LeadService;
import site.easy.to.build.crm.service.ticket.TicketService;
import site.easy.to.build.crm.service.user.UserService;

@Controller
@RequestMapping("/import")
public class ImportCsvController {

    private LeadService leadService;
    private DepenseService depenseService;
    private TicketService ticketService;
    private UserService userService;
    private CustomerService customerService;
    private CustomerLoginInfoService customerLoginInfoService;
    private PasswordEncoder passwordEncoder;
    private CustomerRequestImportService customerRequestImportService;
    private ImportService importService;
    private BudgetService budgetService;

    @Autowired
    public ImportCsvController(DepenseService depenseService, TicketService ticketService, UserService userService,
            CustomerService customerService, CustomerLoginInfoService customerLoginInfoService, PasswordEncoder passwordEncoder,
            CustomerRequestImportService customerRequestImportService, ImportService importService, LeadService leadService,
            BudgetService budgetService) {
        this.depenseService = depenseService;
        this.ticketService = ticketService;
        this.userService = userService;
        this.customerService = customerService;
        this.customerLoginInfoService = customerLoginInfoService;
        this.passwordEncoder = passwordEncoder;
        this.customerRequestImportService = customerRequestImportService;
        this.importService = importService;
        this.leadService = leadService;
        this.budgetService = budgetService;
    }

    @GetMapping("/pageTicketDepense")
    public String importCsv() {
        return "eval/import";
    }

    @PostMapping("/importCustomer")
    public String importCustomer(@RequestParam(name = "file1") MultipartFile file,
            @RequestParam(name = "file2") MultipartFile file2,
            @RequestParam(name = "file3") MultipartFile file3,
            Model model) {
        try {
            System.out.println("Fichier 1 : " + file.getOriginalFilename());
            System.out.println("Fichier 2 : " + file2.getOriginalFilename());
            System.out.println("Fichier 3 : " + file3.getOriginalFilename());

            // importService.importCsv(file, customerService, ',', passwordEncoder, customerLoginInfoService, userService);
            importService.ImportMitambatra(file, file2, file3, customerService, ',', passwordEncoder, customerLoginInfoService,
                    userService, customerRequestImportService, ticketService, leadService, depenseService, budgetService);
            model.addAttribute("message", "importation reussie");
        } catch (Exception e) {
            model.addAttribute("message", e.getMessage());
        }
        return "eval/import";
    }

    @PostMapping("/import-dotnet")
    @ResponseBody
    public ResponseEntity<Map<String, Object>> importComplexCsvApi(@RequestParam("file") MultipartFile file) {
        Map<String, Object> response = new HashMap<>();

        try {
            importService.importDotnetCsv(file, customerService, ticketService, leadService, userService);

            // Vérification immédiate en base
            long customerCount = customerService.count();
            long ticketCount = ((CustomerService) ticketService).count();
            long leadCount = ((CustomerService) leadService).count();

            response.put("success", true);
            response.put("message", "Import successful");
            response.put("stats", Map.of(
                    "customers", customerCount,
                    "tickets", ticketCount,
                    "leads", leadCount
            ));

            return ResponseEntity.ok(response);
        } catch (Exception e) {
            // Log complet de l'erreur
            e.printStackTrace();
            response.put("success", false);
            response.put("message", "Import failed: " + e.getMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(response);
        }
    }

}
