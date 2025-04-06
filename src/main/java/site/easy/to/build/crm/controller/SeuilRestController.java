package site.easy.to.build.crm.controller;

import java.math.BigDecimal;
import java.time.LocalDateTime;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import site.easy.to.build.crm.entity.Depense;
import site.easy.to.build.crm.entity.Seuil;
import site.easy.to.build.crm.service.depense.DepenseService;
import site.easy.to.build.crm.service.seuil.SeuilService;

@RestController
@RequestMapping("/api/seuil")
public class SeuilRestController {
    

    private final SeuilService seuilService;   
    public SeuilRestController(SeuilService seuilService) {
        this.seuilService = seuilService;
    }

    @GetMapping("/update/{valeur}")
    public ResponseEntity<Seuil> updateSeuil(@PathVariable double valeur) {
        Seuil seuil=new Seuil();
        try {
            seuil.setDateSeuil(LocalDateTime.now());
            seuil.setTaux(BigDecimal.valueOf(valeur));
            seuil = seuilService.addSeuil(seuil);        
        } catch (Exception e) {
            return ResponseEntity.badRequest().build();
        }
        return ResponseEntity.ok(seuil);
    }

    @GetMapping("/voirvaleur")
    public ResponseEntity<Seuil> voirSeuil() {
        System.out.println("voirSeuil");
        Seuil seuil=seuilService.getSeuilActuel();
        System.out.println("seuil"+seuil.getTaux());
        return ResponseEntity.ok(seuil);
    }
    

}
