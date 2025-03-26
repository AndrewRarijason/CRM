package site.easy.to.build.crm.service.importcsv;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVParser;
import org.apache.commons.csv.CSVRecord;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import jakarta.transaction.Transactional;
import site.easy.to.build.crm.entity.Budget;
import site.easy.to.build.crm.entity.Customer;
import site.easy.to.build.crm.entity.CustomerLoginInfo;
import site.easy.to.build.crm.entity.Depense;
import site.easy.to.build.crm.entity.Lead;
import site.easy.to.build.crm.entity.Ticket;
import site.easy.to.build.crm.entity.TicketDepense;
import site.easy.to.build.crm.entity.User;
import site.easy.to.build.crm.entity.importcsv.CustomerRequestImport;
import site.easy.to.build.crm.service.budget.BudgetService;
import site.easy.to.build.crm.service.customer.CustomerLoginInfoService;
import site.easy.to.build.crm.service.customer.CustomerService;
import site.easy.to.build.crm.service.depense.DepenseService;
import site.easy.to.build.crm.service.lead.LeadService;
import site.easy.to.build.crm.service.ticket.TicketService;
import site.easy.to.build.crm.service.user.UserService;
import site.easy.to.build.crm.util.EmailTokenUtils;

@Service
public class ImportService {
    
    // @Transactional
    public void importCsv(MultipartFile file,CustomerService customerService,char separator,
    PasswordEncoder passwordEncoder,CustomerLoginInfoService customerLoginInfoService,
    UserService userService) 
    throws Exception {
        List<Customer> customers = new ArrayList<>();
        // List<CustomerLoginInfo> customerLoginInfos = new ArrayList<>();
        
        int line=2;            

        try (BufferedReader reader = new BufferedReader(new InputStreamReader(file.getInputStream(), StandardCharsets.UTF_8));
             @SuppressWarnings("deprecation")
             CSVParser csvParser = new CSVParser(reader, CSVFormat.DEFAULT
                     .withFirstRecordAsHeader()
                     .withDelimiter(separator))) {  // Spécifiez le séparateur ici

            for (CSVRecord csvRecord : csvParser) {

                if (csvRecord.size() == 2) {
                    Customer customer = new Customer();
                    String nomCustomer=csvRecord.get("customer_name").trim();
                    if (nomCustomer==null||nomCustomer.isEmpty()) {
                        throw new Exception("Nom du client manquant ");
                        
                    }
                    customer.setName(nomCustomer);
                    String emailCustomer=csvRecord.get("customer_email").trim();
                    if (emailCustomer == null || emailCustomer.isEmpty()) {
                        throw new Exception("Email du client manquant " );
                    }
                    customer.setEmail(emailCustomer);

                    // login info
                    CustomerLoginInfo customerLoginInfo = new CustomerLoginInfo();
                    customerLoginInfo.setEmail(emailCustomer);
                    customerLoginInfo.setToken(EmailTokenUtils.generateToken());
                    customerLoginInfo.setPasswordSet(true);
                    customerLoginInfo.setPassword(passwordEncoder.encode("123"));

                    CustomerLoginInfo customerLoginInfo2=customerLoginInfoService.save(customerLoginInfo);
                    
                    customer.setCustomerLoginInfo(customerLoginInfo2);
                    
                    customer.setPosition("imported");
                    customer.setPhone("0000000000");
                    customer.setAddress("imported");
                    customer.setCity("imported");
                    customer.setState("imported");
                    customer.setCountry("Madgascar");
                    customer.setCreatedAt(LocalDateTime.now());
                    customer.setDescription("imported");


                    User user = userService.findById(53);
                    customer.setUser(user);

                    Customer customer2=customerService.save(customer);

                    customers.add(customer2);

                    line ++;
                
                } else {
                    throw new Exception("Invalid CSV file");
                    
                }
            }

        } catch (Exception e) {
            e.printStackTrace();

            throw new Exception("Erreur lors de la lecture du fichier CSV : " + e.getMessage()+" à la ligne "+line);
        }
    }

    
    // @Transactional
    public void readOthercsv(MultipartFile file,CustomerRequestImportService requestImportService,char separator,
    CustomerService customerService,UserService userService,TicketService ticketService,LeadService leadService,
    DepenseService depenseService)
    throws Exception{

        List<User> users=userService.findByRoles_Name("ROLE_MANAGER");
        List<User> users2=userService.findByRoles_Name("ROLE_EMPLOYEE");
        users2.addAll(users);
        
        
        int line=2;            

        try (BufferedReader reader = new BufferedReader(new InputStreamReader(file.getInputStream(), StandardCharsets.UTF_8));
             @SuppressWarnings("deprecation")
             CSVParser csvParser = new CSVParser(reader, CSVFormat.DEFAULT
                     .withFirstRecordAsHeader()
                     .withDelimiter(separator))) {  // Spécifiez le séparateur ici

            
            for (CSVRecord csvRecord : csvParser) {
                System.out.println(csvRecord.size());
                if (csvRecord.size()==5) {

                    int randomNumber=(int)(Math.random()*users.size());
                    int randomNumber2=(int)(Math.random()*users2.size());



                    String mail=csvRecord.get("customer_email").trim();
                    String subject=csvRecord.get("subject_or_name").trim();
                    String type=csvRecord.get("type").trim();
                    String status=csvRecord.get("status").trim();
                    
                    String expense=csvRecord.get("expense").trim();

                    Customer customer=customerService.findByEmail(mail);

                    if (type.equals("lead")) {
                        Lead lead=new Lead();
                        lead.setCustomer(customer);
                        lead.setName(subject);
                        lead.setStatus(status);
                        lead.setCreatedAt(LocalDateTime.now());
                        lead.setPhone("0000000000");
                        lead.setManager(users.get(randomNumber));
                        lead.setEmployee(users2.get(randomNumber2));

                        Lead lead2=leadService.save(lead);

                        
                         try {
                            expense = expense.replace(',', '.');

                            Double expensed=Double.parseDouble(expense);
                            if (expensed<=0) {
                                throw new Exception("valeur negative");
                            }


                            Depense depense=new Depense();


                            depense.setLead(lead2);
                            depense.setValeurDepense(expensed);
                            depense.setDateDepense(LocalDateTime.now());
                            depense.setEtat(1);

                            Depense depense2=depenseService.saveDepense(depense);
                            


                        } catch (Exception e) {
                            throw new Exception("Invalid expense value"+e.getMessage());

                        }
                    }
                    else if (type.equals("ticket")) {
                        Ticket ticket=new Ticket();
                        ticket.setCustomer(customer);
                        ticket.setSubject(subject);
                        ticket.setStatus(status);
                        ticket.setCreatedAt(LocalDateTime.now());
                        ticket.setManager(users.get(randomNumber));
                        ticket.setEmployee(users2.get(randomNumber2));
                        ticket.setPriority("low");

                        
                        Ticket ticket2= ticketService.save(ticket);

                         try {
                        

                            expense = expense.replace(',', '.');
                            Double expensedo=Double.parseDouble(expense);

                            
                            if (expensedo<=0) {
                                throw new Exception("valeur negative");
                            }


                            Depense depense=new Depense();
                            depense.setTicket(ticket2);
                            depense.setValeurDepense(expensedo);
                            depense.setDateDepense(LocalDateTime.now());
                            depense.setEtat(1);

                            Depense depense2=depenseService.saveDepense(depense);

                        } catch (Exception e) {
                            throw new Exception("Invalid expense value");
                        }
                    }
                    else{
                        throw new Exception("Invalid type value");
                    }

                    line++;
                
                
                }else{
                    throw new Exception("Invalid CSV file ++"+csvRecord.size());
                }
            }

        } catch (Exception e) {
            e.printStackTrace();
            throw new Exception("Erreur lors de la lecture du fichier CSV : " + e.getMessage()+" à la ligne "+line);
        }

    }



    // @Transactional
    public void readBudgetcsv(MultipartFile file,BudgetService budgetService,char separator,
    CustomerService customerService)
    throws Exception{

        
        
        int line=2;            

        try (BufferedReader reader = new BufferedReader(new InputStreamReader(file.getInputStream(), StandardCharsets.UTF_8));
             @SuppressWarnings("deprecation")
             CSVParser csvParser = new CSVParser(reader, CSVFormat.DEFAULT
                     .withFirstRecordAsHeader()
                     .withDelimiter(separator))) {  // Spécifiez le séparateur ici

            
            for (CSVRecord csvRecord : csvParser) {
                System.out.println(csvRecord.size());
                if (csvRecord.size()==2) {
                    String customerMail=csvRecord.get("customer_email").trim();
                    String budgetValue=csvRecord.get("Budget").trim();
                    Customer customer=customerService.findByEmail(customerMail);


                    Budget budget=new Budget();
                    try {
                        budgetValue = budgetValue.replace(',', '.');
                        budget.setValeur(Double.parseDouble(budgetValue));

                        if (Double.parseDouble(budgetValue)<=0) {
                            throw new Exception("valeur negative");
                        }

                        budget.setCustomer(customer);
                        budget.setDate(LocalDateTime.now());
                        budgetService.save(budget);

                    } catch (Exception e) {
                        throw new Exception("Invalid budget value");
                    }
                    
                }else{
                    throw new Exception("Invalid CSV file ++"+csvRecord.size());
                }
            }

        } catch (Exception e) {
            e.printStackTrace();
            throw new Exception("Erreur lors de la lecture du fichier CSV : " + e.getMessage()+" à la ligne "+line);
        }

    }



    @Transactional(rollbackOn = Exception.class)
    public void ImportMitambatra(MultipartFile file,MultipartFile file2,MultipartFile file3,CustomerService customerService,char separator,
    PasswordEncoder passwordEncoder,CustomerLoginInfoService customerLoginInfoService,
    UserService userService,CustomerRequestImportService requestImportService,TicketService ticketService
    ,LeadService leadService,DepenseService depenseService,BudgetService budgetService) throws Exception{
        int filenb=1;
        try {
            importCsv(file, customerService, separator, passwordEncoder, customerLoginInfoService, userService);
            filenb++;
            readBudgetcsv(file2, budgetService , separator, customerService);
            readOthercsv(file3, requestImportService, separator, customerService, 
            userService, ticketService, leadService, depenseService);
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException(e+"sur le ficker"+filenb);
        }
    }


    


}