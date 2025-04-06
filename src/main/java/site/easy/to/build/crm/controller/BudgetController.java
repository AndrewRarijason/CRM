package site.easy.to.build.crm.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import site.easy.to.build.crm.entity.Budget;
import site.easy.to.build.crm.entity.Customer;
import site.easy.to.build.crm.service.budget.BudgetService;
import site.easy.to.build.crm.service.customer.CustomerService;
import site.easy.to.build.crm.util.AuthenticationUtils;

import java.util.List;

@Controller
@RequestMapping("/budgets")
public class BudgetController {

    private final BudgetService budgetService;

    private final AuthenticationUtils authenticationUtils;
    private final CustomerService customerService;

    @Autowired
    public BudgetController(BudgetService budgetService, AuthenticationUtils authenticationUtils, CustomerService customerService) {
        this.budgetService = budgetService;
        this.authenticationUtils = authenticationUtils;
        this.customerService = customerService;
    }


    @PostMapping("/save")
    public String saveBudget(@ModelAttribute Budget budget,Authentication authentication){
        int userId = authenticationUtils.getLoggedInUserId(authentication);
        Customer customer = customerService.findByCustomerId(userId);
        budget.setCustomer(customer);
        budgetService.save(budget);
        return "redirect:/budgets/create";
    }
    @GetMapping("/customerBudget")
    public String showCreatedCustomerBudget(Authentication authentication,Model model){
        int userId = authenticationUtils.getLoggedInUserId(authentication);
        List<Budget> budgets = budgetService.findByCustomerId(userId);
        double valeur = budgetService.getTotalBudgetByCustomerId(userId);
        model.addAttribute("budgets",budgets);
        model.addAttribute("valuer",valeur);
        return "budget/myBudget";
    }
    @GetMapping("/create")
    public String showCreateForm(Model model) {
        model.addAttribute("budget", new Budget());
        return "budget/form";
    }
}
