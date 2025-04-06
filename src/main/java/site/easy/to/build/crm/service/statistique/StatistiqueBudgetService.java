package site.easy.to.build.crm.service.statistique;

import site.easy.to.build.crm.entity.StatistiqueBudget;
import site.easy.to.build.crm.repository.StatistiqueBudgetRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class StatistiqueBudgetService {

    @Autowired
    private StatistiqueBudgetRepository repository;

    public List<StatistiqueBudget> getAllStatistiques() {
        return repository.findAll();
    }
}
