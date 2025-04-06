package site.easy.to.build.crm.entity;

import java.util.List;

public class DashboardEntity {
    private List<StatistiqueBudget> statistiqueBudgets;
    private DepenseStat depenseStat;
    public List<StatistiqueBudget> getStatistiqueBudgets() {
        return statistiqueBudgets;
    }
    public void setStatistiqueBudgets(List<StatistiqueBudget> statistiqueBudgets) {
        this.statistiqueBudgets = statistiqueBudgets;
    }
    public DepenseStat getDepenseStat() {
        return depenseStat;
    }
    public void setDepenseStat(DepenseStat depenseStat) {
        this.depenseStat = depenseStat;
    }

}
