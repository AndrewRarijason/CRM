package site.easy.to.build.crm.controller;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import site.easy.to.build.crm.service.customer.CustomerService;
import site.easy.to.build.crm.service.importcsv.ImportService;
import site.easy.to.build.crm.service.lead.LeadService;
import site.easy.to.build.crm.service.ticket.TicketService;
import site.easy.to.build.crm.service.user.UserService;

@RestController
@RequestMapping("/api/import")
public class ImportApiController {

    @Autowired
    private ImportService importService;

    @Autowired
    private CustomerService customerService;

    @Autowired
    private TicketService ticketService;

    @Autowired
    private LeadService leadService;

    @Autowired
    private UserService userService;

    @PostMapping("/dotnet")
    public ResponseEntity<Map<String, Object>> importCsv(@RequestParam("file") MultipartFile file) {
        try {
            importService.importDotnetCsv(file, customerService, ticketService, leadService, userService);
            
            Map<String, Object> response = new HashMap<>();
            response.put("success", true);
            response.put("message", "Import completed successfully");
            return ResponseEntity.ok(response);
            
        } catch (Exception e) {
            Map<String, Object> errorResponse = new HashMap<>();
            errorResponse.put("success", false);
            errorResponse.put("error", e.getMessage());
            return ResponseEntity.badRequest().body(errorResponse);
        }
    }
}