package site.easy.to.build.crm.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import site.easy.to.build.crm.entity.Budget;
import site.easy.to.build.crm.entity.DashboardEntity;
import site.easy.to.build.crm.entity.DepenseStat;
import site.easy.to.build.crm.entity.StatistiqueBudget;
import site.easy.to.build.crm.service.budget.BudgetService;
import site.easy.to.build.crm.service.statistique.DepenseStatService;
import site.easy.to.build.crm.service.statistique.StatistiqueBudgetService;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api")
@CrossOrigin(origins = "*") // Permet les appels CORS depuis n'importe quel domaine
public class BudgetRestController {

    private final BudgetService budgetService;
    private final StatistiqueBudgetService statistiqueBudgetService;
    private final DepenseStatService depenseStatService;

    public BudgetRestController(BudgetService budgetService, StatistiqueBudgetService statistiqueBudgetService,
    DepenseStatService depenseStatService) {
        this.budgetService = budgetService;
        this.statistiqueBudgetService = statistiqueBudgetService;
        this.depenseStatService = depenseStatService;
    }

    @GetMapping("/budgets")
    public ResponseEntity<List<Budget>> getAllBudgets() {
        List<Budget> budgets = budgetService.findAll();
        return ResponseEntity.ok(budgets);
    }


    @GetMapping("/statistiques")
    public ResponseEntity<DashboardEntity> getAllStatistiques() {
        List<StatistiqueBudget> statistiques = statistiqueBudgetService.getAllStatistiques();
        DepenseStat depenseStat=depenseStatService.getDepenseStats();
        DashboardEntity dashboardEntity=new DashboardEntity();
        dashboardEntity.setStatistiqueBudgets(statistiques);
        dashboardEntity.setDepenseStat(depenseStat);
        return ResponseEntity.ok(dashboardEntity);
    }


    @GetMapping("/budgets/customer/{customerId}")
    public ResponseEntity<List<Budget>> getBudgetsByCustomerId(@PathVariable int customerId) {
        List<Budget> budgets = budgetService.findByCustomerId(customerId);
        if (budgets.isEmpty()) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok(budgets);
    }

   
}
