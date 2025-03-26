package site.easy.to.build.crm.service.statistique;

import site.easy.to.build.crm.entity.DepenseStat;
import site.easy.to.build.crm.repository.DepenseStatRepository;
import org.springframework.stereotype.Service;

@Service
public class DepenseStatService {

    private final DepenseStatRepository depenseStatRepository;

    public DepenseStatService(DepenseStatRepository depenseStatRepository) {
        this.depenseStatRepository = depenseStatRepository;
    }

    public DepenseStat getDepenseStats() {
        return depenseStatRepository.findFirstBy()
                .orElseThrow(() -> new RuntimeException("Aucune donnée trouvée dans la vue v_depense_stat"));
    }
}
