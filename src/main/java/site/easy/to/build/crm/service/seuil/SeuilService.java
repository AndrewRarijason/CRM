package site.easy.to.build.crm.service.seuil;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import site.easy.to.build.crm.entity.Seuil;
import site.easy.to.build.crm.repository.SeuilRepository;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
public class SeuilService {

    private final SeuilRepository seuilRepository;

    @Autowired
    public SeuilService(SeuilRepository seuilRepository) {
        this.seuilRepository = seuilRepository;
    }

    // Récupérer tous les seuils
    public List<Seuil> getAllSeuils() {
        return seuilRepository.findAll();
    }

    // Récupérer un seuil par ID
    public Optional<Seuil> getSeuilById(int id) {
        return seuilRepository.findById(id);
    }

   

    // Ajouter un nouveau seuil
    public Seuil addSeuil(Seuil seuil) {
        return seuilRepository.save(seuil);
    }

    // Mettre à jour un seuil
    public Seuil updateSeuil(int id, Seuil updatedSeuil) {
        return seuilRepository.findById(id).map(seuil -> {
            seuil.setTaux(updatedSeuil.getTaux());
            seuil.setDateSeuil(updatedSeuil.getDateSeuil());
            return seuilRepository.save(seuil);
        }).orElseThrow(() -> new RuntimeException("Seuil non trouvé avec l'ID : " + id));
    }

    
    public void deleteSeuil(int id) {
        if (seuilRepository.existsById(id)) {
            seuilRepository.deleteById(id);
        } else {
            throw new RuntimeException("Seuil non trouvé avec l'ID : " + id);
        }
    }

     public Seuil getSeuilActuel() {
        LocalDateTime now = LocalDateTime.now();

        List<Seuil> seuils = seuilRepository.findAll();

        Optional<Seuil> seuilActuel = seuils.stream()
            .min((seuil1, seuil2) -> {
                long diff1 = Math.abs(seuil1.getDateSeuil().until(now, java.time.temporal.ChronoUnit.SECONDS));
                long diff2 = Math.abs(seuil2.getDateSeuil().until(now, java.time.temporal.ChronoUnit.SECONDS));
                return Long.compare(diff1, diff2);
            });

        return seuilActuel.orElse(null);
    }
}
