package ma.entraide.formationcentres.Controller;

import ma.entraide.formationcentres.Entity.Beneficiaire;
import ma.entraide.formationcentres.Entity.BeneficiaireRequest;
import ma.entraide.formationcentres.Service.BeneficiaireService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
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
    public ResponseEntity<Beneficiaire> addBeneficiaire(@RequestBody BeneficiaireRequest request) {
    	Beneficiaire beneficiaire = new Beneficiaire(
                request.getNom(), request.getPrenom(), request.getAdresse(), 
                request.getTelephone(), request.getDateNaissance(), request.getSexe(), 
                request.getCin(),request.getNationalite(),
                request.isSituationHandicap(),request.getNumCarteHandicap(), request.getCommune(),
                request.getProvince()
            );
        Beneficiaire savedBeneficiaire = beneficiaireService.saveBeneficiaire(beneficiaire, request.getSuivies());

        return ResponseEntity.ok(savedBeneficiaire);
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