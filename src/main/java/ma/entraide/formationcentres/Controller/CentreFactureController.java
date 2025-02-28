package ma.entraide.formationcentres.Controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import ma.entraide.formationcentres.Entity.CentreFacture;
import ma.entraide.formationcentres.Service.FactureCentreService;

@RestController
@RequestMapping("/facture")
public class CentreFactureController {
	@Autowired
    private FactureCentreService centrefactureService;

    @GetMapping("/all")
    public ResponseEntity<List<CentreFacture>> getAllFactures() {
        List<CentreFacture> factures = centrefactureService.getFactures();
        return ResponseEntity.ok(factures);
    }

    @GetMapping("/{id}")
    public ResponseEntity<CentreFacture> getFactureById(@PathVariable Long id) {
        try {
            CentreFacture centrefacture = centrefactureService.getFacture(id);
            return ResponseEntity.ok(centrefacture);
        } catch (Exception e) {
            return ResponseEntity.notFound().build();
        }
    }
    @GetMapping("/Centre/{id}")
    public ResponseEntity<List<CentreFacture>> getFactureByCentreId(@PathVariable Long id) {
        try {
            List<CentreFacture> factures = centrefactureService.getFactureByCentre(id);
            return ResponseEntity.ok(factures);
        } catch (Exception e){
            return ResponseEntity.notFound().build();
        }
    }
    @PostMapping("/add")
    public ResponseEntity<CentreFacture> addFacture(@RequestBody CentreFacture centrefacture) {
        try {
            CentreFacture newFacture = centrefactureService.createFacture(centrefacture);
            return ResponseEntity.ok(newFacture);
        } catch (Exception e) {
            return ResponseEntity.badRequest().build();
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<CentreFacture> updateFacture(@PathVariable Long id,@RequestBody CentreFacture centrefacture) {
        try {
            CentreFacture updatedFacture = centrefactureService.updateFacture(id,centrefacture);
            return ResponseEntity.ok(updatedFacture);
        } catch (Exception e) {
            return ResponseEntity.badRequest().build();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteFacture(@PathVariable Long id) {
        try {
        	centrefactureService.deleteFacture(id);
            return ResponseEntity.ok("Facture deleted successfully");
        } catch (Exception e) {
            return ResponseEntity.badRequest().build();
        }
    }
}
