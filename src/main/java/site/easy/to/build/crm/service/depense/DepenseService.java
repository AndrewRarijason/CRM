package site.easy.to.build.crm.service.depense;

import java.util.List;
import java.util.Optional;

import site.easy.to.build.crm.entity.Depense;

public interface DepenseService {
    List<Depense> getAllDepenses();
    Optional<Depense> getDepenseById(Integer id);
    Depense saveDepense(Depense depense);
    void deleteDepense(Integer id);
    double getTotalDepenseByCustomerId(int customerId);
    Depense updateDepense(Integer id, Depense updatedDepense);
  
}
