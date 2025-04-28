package ma.entraide.formationcentres.Controller;


import ma.entraide.formationcentres.Entity.Beneficiaire;
import ma.entraide.formationcentres.Entity.Suivie;

import ma.entraide.formationcentres.Service.SuivieService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/suivies")
public class SuivieController {

    @Autowired
    private SuivieService suivieService;

    @GetMapping
    public ResponseEntity<List<Suivie>> getAllSuivies() {
        return ResponseEntity.ok(suivieService.getAllSuivies());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Suivie> getSuivieById(@PathVariable Long id) {
        return ResponseEntity.ok(suivieService.getSuivie(id));
    }
    @GetMapping("/beneficiaire/{beneficiaireId}")
    public ResponseEntity<List<Suivie>> getSuiviesByBeneficiaire(@PathVariable Long beneficiaireId) {
        List<Suivie> suivies = suivieService.getSuiviesByBeneficiaire(beneficiaireId);
        if (suivies.isEmpty()) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok(suivies);
    }
    @PostMapping("/{beneficiaireId}")
    public ResponseEntity<Suivie> addSuivie(@PathVariable Long beneficiaireId, @RequestBody Suivie dto) {
        return ResponseEntity.ok(suivieService.saveSuivie(beneficiaireId, dto));
    }

    @PutMapping("/{id}")
    public ResponseEntity<Suivie> updateSuivie(@PathVariable Long id, @RequestBody Suivie updatedSuivie) {
        return ResponseEntity.ok(suivieService.updateSuivie(id, updatedSuivie));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteSuivie(@PathVariable Long id) {
        suivieService.deleteSuivie(id);
        return ResponseEntity.noContent().build();
    }
}
