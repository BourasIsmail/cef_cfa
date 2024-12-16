package ma.entraide.formationcentres.Controller;

import ma.entraide.formationcentres.Entity.Beneficiaire;
import ma.entraide.formationcentres.Service.BeneficiaireService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin("*")
@RequestMapping("/beneficiaire")
public class BeneficiaireController {
    @Autowired
    private BeneficiaireService beneficiaireService;

    @GetMapping("/all")
    public ResponseEntity<List<Beneficiaire>> getAllBeneficiaires() {
        List<Beneficiaire> beneficiaires = beneficiaireService.getAllBeneficiaire();
        return ResponseEntity.ok(beneficiaires);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Beneficiaire> getBeneficiaireById(@PathVariable Long id) {
        try {
            Beneficiaire beneficiaire = beneficiaireService.getBeneficiaireById(id);
            return ResponseEntity.ok(beneficiaire);
        } catch (Exception e) {
            return ResponseEntity.notFound().build();
        }
    }

    @PostMapping
    public  ResponseEntity<Beneficiaire> addBeneficiaire(@RequestBody Beneficiaire beneficiaire) {
        try {
            Beneficiaire newBeneficiaire = beneficiaireService.createBeneficiaire(beneficiaire);
            return ResponseEntity.ok(newBeneficiaire);
        } catch (Exception e) {
            return ResponseEntity.badRequest().build();
        }
    }

    @PutMapping
    public ResponseEntity<Beneficiaire> updateBeneficiaire(@RequestBody Beneficiaire beneficiaire) {
        try {
            Beneficiaire updatedBeneficiaire = beneficiaireService.updateBeneficiaire(beneficiaire);
            return ResponseEntity.ok(updatedBeneficiaire);
        } catch (Exception e) {
            return ResponseEntity.badRequest().build();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteBeneficiaire(@PathVariable Long id) {
        try {
            beneficiaireService.deleteBeneficiaireById(id);
            return ResponseEntity.ok("Beneficiaire deleted successfully");
        } catch (Exception e) {
            return ResponseEntity.badRequest().build();
        }
    }

}
