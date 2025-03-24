package site.easy.to.build.crm.service.budget;


import org.apache.el.lang.ELArithmetic.BigDecimalDelegate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cglib.core.Local;
import org.springframework.stereotype.Service;
import site.easy.to.build.crm.entity.Budget;
import site.easy.to.build.crm.entity.Customer;
import site.easy.to.build.crm.entity.Depense;
import site.easy.to.build.crm.entity.Notification;
import site.easy.to.build.crm.entity.Seuil;
import site.easy.to.build.crm.repository.BudgetRepository;
import site.easy.to.build.crm.repository.DepenseRepository;
import site.easy.to.build.crm.service.customer.CustomerService;
import site.easy.to.build.crm.service.depense.DepenseService;
import site.easy.to.build.crm.service.depense.DepenseServiceImpl;
import site.easy.to.build.crm.service.seuil.SeuilService;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
public class BudgetService {
    
    private final BudgetRepository budgetRepository;
    private final DepenseService depenseService;
    private final SeuilService seuilService;
    private final CustomerService customerService;

    @Autowired
    public BudgetService(BudgetRepository budgetRepository, DepenseService depenseService, SeuilService seuil ,CustomerService customerService) {
        this.budgetRepository = budgetRepository;
        this.depenseService = depenseService;
        this.seuilService = seuil;
        this.customerService=customerService;
    }

    public List<Budget> findAll(){
        return budgetRepository.findAll();
    }
    public Optional<Budget> findById(int id){
        return budgetRepository.findById(id);
    }
    public Budget save(Budget budget){
        return budgetRepository.save(budget);
    }
    public void delete(int id){
        budgetRepository.deleteById(id);
    }
    public List<Budget> findByCustomerId(int customerId){
        return budgetRepository.findByCustomerCustomerId(customerId);
    }
    public double getTotalBudgetByCustomerId(int customerId) {
        return budgetRepository.getTotalBudgetByCustomerId(customerId);
    }
    
   public Notification checkBudget(int customerId,double newDepense){
        double totalDepense = depenseService.getTotalDepenseByCustomerId(customerId)+newDepense;
        System.out.println("total depense"+totalDepense);
        double seuil = seuilService.getSeuilActuel().getTaux().doubleValue();
        double budget = getTotalBudgetByCustomerId(customerId);
        
        double seuilBudget = budget * (seuil/100);
        LocalDateTime date= LocalDateTime.now();
        Customer cust= customerService.findByCustomerId(customerId);
        if( totalDepense > seuilBudget){
            return  new Notification("Le seuil du budget est dépassé",date,0,cust);
             
        }else if (totalDepense > budget){
            return new Notification("Le budget est dépassé",date,0,cust);
        }else if (totalDepense==seuilBudget) {
            return new Notification("le sueil du budget est atteint",date,1,cust);

        }else{
            return new Notification("successful", date, 1, cust);
        } 
}

}

