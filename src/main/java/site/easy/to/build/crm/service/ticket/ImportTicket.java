package site.easy.to.build.crm.service.ticket;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVParser;
import org.apache.commons.csv.CSVRecord;
import org.springframework.web.multipart.MultipartFile;

import site.easy.to.build.crm.entity.Customer;
import site.easy.to.build.crm.entity.Depense;
import site.easy.to.build.crm.entity.Ticket;
import site.easy.to.build.crm.entity.TicketDepense;
import site.easy.to.build.crm.entity.User;
import site.easy.to.build.crm.service.customer.CustomerService;
import site.easy.to.build.crm.service.depense.DepenseService;
import site.easy.to.build.crm.service.user.UserService;

public class ImportTicket {


    public List<TicketDepense> importTicket(MultipartFile file,char separator,DepenseService depenseService,
    TicketService ticketService,UserService userService,CustomerService customerService) throws Exception {

        List<TicketDepense> ticketDepenseList = new ArrayList<>();
        

        try (BufferedReader reader = new BufferedReader(new InputStreamReader(file.getInputStream(), StandardCharsets.UTF_8));
             @SuppressWarnings("deprecation")
             CSVParser csvParser = new CSVParser(reader, CSVFormat.DEFAULT
                     .withFirstRecordAsHeader()
                     .withDelimiter(separator))) {  // Spécifiez le séparateur ici

            for (CSVRecord csvRecord : csvParser) {
                // T instance = clazz.getDeclaredConstructor().newInstance();

                Ticket ticket = new Ticket();
                
                String subject = csvRecord.get("subject").trim();
                ticket.setSubject(subject);
                String  description= csvRecord.get("description").trim();
                ticket.setDescription(description);
                String status = csvRecord.get("status").trim();
                ticket.setStatus(status);
                String priority = csvRecord.get("priority").trim();
                ticket.setPriority(priority);
                String manager = csvRecord.get("manager").trim();
                int managerId = Integer.parseInt(manager);
                User managerUser=userService.findById(managerId);
                ticket.setManager(managerUser);
                if (managerUser==null){
                    throw new Exception("Manager not found");
                    
                }


                String employee = csvRecord.get("employee").trim();
                int employeeId = Integer.parseInt(employee);
                User employeeUser=userService.findById(employeeId);
                ticket.setEmployee(employeeUser);
                if (employeeUser==null){
                    throw new Exception("Employee not found");
                    
                }

                String customer = csvRecord.get("customer").trim();
                int customerId = Integer.parseInt(customer);
                Customer customerUser=customerService.findByCustomerId(customerId);
                ticket.setCustomer(customerUser);
                if (customerUser==null){
                    throw new Exception("Customer not found");
                    
                }

                System.out.println("tonga PARSE DATE");
                String createdAt = csvRecord.get("created_at").trim();
                System.out.println("datePARSENA"+createdAt);
                LocalDateTime createdAtDate = LocalDateTime.parse(createdAt);

                ticket.setCreatedAt(createdAtDate);

                Depense depenseObj = new Depense();

                String depense= csvRecord.get("depense").trim();
                double depenseValue = Double.parseDouble(depense);
                depenseObj.setValeurDepense(depenseValue);

                
                depenseObj.setDateDepense(createdAtDate);
                depenseObj.setEtat(1);

                depenseObj.setTicket(ticket);
                

                TicketDepense ticketDepense = new TicketDepense(ticket, depenseObj);
                ticketDepenseList.add(ticketDepense);
            }

        } catch (Exception e) {
            e.printStackTrace();
            throw new Exception("Erreur lors de la lecture du fichier CSV : " + e.getMessage());
        }
        return ticketDepenseList;
    }


    public boolean insertCSV(List <TicketDepense> ticketDepenseList,DepenseService depenseService,TicketService ticketService){
        for (TicketDepense ticketDepense : ticketDepenseList) {
            Ticket ticket = ticketDepense.getTicket();
            Depense depense = ticketDepense.getDepense();
            Ticket saveTicket=ticketService.save(ticket);
            ticketDepense.getDepense().setTicket(saveTicket);
            ticketDepense.setTicket(saveTicket); 
            Depense depense2=depenseService.saveDepense(depense);
            ticketDepense.setDepense(depense2);
        }
        return true;
    }
}
