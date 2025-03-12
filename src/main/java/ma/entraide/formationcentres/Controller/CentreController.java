package ma.entraide.formationcentres.Controller;

import ma.entraide.formationcentres.Entity.Centre;
import ma.entraide.formationcentres.Service.CentreService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin("*")
@RequestMapping("/centres")
public class CentreController {
    @Autowired
    private CentreService centreService;
    @GetMapping
    public ResponseEntity<List<Centre>> getAllCentres() {
        return ResponseEntity.ok(centreService.getCentres());
    }
    
    @GetMapping("/{id}")
    public ResponseEntity<Centre> getBeneficiaireById(@PathVariable Long id) {
        return ResponseEntity.ok(centreService.getCentre(id));
    }
    @GetMapping("/Province/{id}")
    public ResponseEntity<List<Centre>> getBeneficiaireByProvinceId(@PathVariable Long id) {
        return ResponseEntity.ok(centreService.getCentreByProvince(id));
    }
    @PostMapping
    public ResponseEntity<Centre> addCentre(@RequestBody Centre centre) {
        return ResponseEntity.ok(centreService.saveCentre(centre));
    }

    @PutMapping("/{id}")
    public ResponseEntity<Centre> updateCentre(@PathVariable Long id, @RequestBody Centre updatedCentre) {
        return ResponseEntity.ok(centreService.updateCentre(id, updatedCentre));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteCentre(@PathVariable Long id) {
    	centreService.deleteCentre(id);
        return ResponseEntity.noContent().build();
    }
}
