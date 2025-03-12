package ma.entraide.formationcentres.Controller;

import ma.entraide.formationcentres.Entity.Beneficiaire;
import ma.entraide.formationcentres.Service.BeneficiaireService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin("*")
@RequestMapping("/beneficiaires")
public class BeneficiaireController {

    @Autowired
    private BeneficiaireService beneficiaireService;

    @GetMapping
    public ResponseEntity<List<Beneficiaire>> getAllBeneficiaires() {
        return ResponseEntity.ok(beneficiaireService.getAllBeneficiaires());
    }
    
    @GetMapping("/{id}")
    public ResponseEntity<Beneficiaire> getBeneficiaireById(@PathVariable Long id) {
        return ResponseEntity.ok(beneficiaireService.getBeneficiaire(id));
    }
    @GetMapping("/Province/{id}")
    public ResponseEntity<List<Beneficiaire>> getBeneficiaireByProvinceId(@PathVariable Long id) {
        return ResponseEntity.ok(beneficiaireService.getBeneficiaireByProvince(id));
    }
    @PostMapping
    public ResponseEntity<Beneficiaire> addBeneficiaire(@RequestBody Beneficiaire beneficiaire) {
        return ResponseEntity.ok(beneficiaireService.saveBeneficiaire(beneficiaire));
    }

    @PutMapping("/{id}")
    public ResponseEntity<Beneficiaire> updateBeneficiaire(@PathVariable Long id, @RequestBody Beneficiaire updatedBeneficiaire) {
        return ResponseEntity.ok(beneficiaireService.updateBeneficiaire(id, updatedBeneficiaire));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteBeneficiaire(@PathVariable Long id) {
        beneficiaireService.deleteBeneficiaire(id);
        return ResponseEntity.noContent().build();
    }
}