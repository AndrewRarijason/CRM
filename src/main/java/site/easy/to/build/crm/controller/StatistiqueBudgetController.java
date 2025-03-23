package site.easy.to.build.crm.controller;

import site.easy.to.build.crm.entity.StatistiqueBudget;
import site.easy.to.build.crm.service.statistique.StatistiqueBudgetService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/statistiques")
public class StatistiqueBudgetController {

    @Autowired
    private StatistiqueBudgetService service;

    @GetMapping
    public List<StatistiqueBudget> getAllStatistiques() {
        return service.getAllStatistiques();
    }
}
