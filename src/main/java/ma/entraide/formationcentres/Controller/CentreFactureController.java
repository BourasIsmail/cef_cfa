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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import ma.entraide.formationcentres.Entity.CentreFacture;
import ma.entraide.formationcentres.Repository.CentreFactureRepo;
import ma.entraide.formationcentres.Service.FactureCentreService;

@RestController
@RequestMapping("/factures")
public class CentreFactureController {
	@Autowired
    private FactureCentreService centrefactureService;
	
	@Autowired
	private CentreFactureRepo centreFactureRepo;
	@GetMapping
    public ResponseEntity<List<CentreFacture>> getAllFactures() {
        return ResponseEntity.ok(centrefactureService.getFactures());
    }

    
    @PostMapping("/add")
    public CentreFacture addFactureToCentre(@RequestBody CentreFacture centreFacture, @RequestParam Long centreId) {

        double total = centreFacture.getEau() + centreFacture.getElectricite();
        centreFacture.setTotal(total);

        return centreFactureRepo.save(centreFacture);
    }
    @GetMapping("/centre/{centreId}")
    public ResponseEntity<List<CentreFacture>> getFacturesByCentre(@PathVariable Long centreId) {
        List<CentreFacture> factures = centrefactureService.getFacturesByCentre(centreId);
        if (factures.isEmpty()) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok(factures);
    }
    

    
    @GetMapping("/{id}")
    public ResponseEntity<CentreFacture> getFactureById(@PathVariable Long id) {
        return ResponseEntity.ok(centrefactureService.getFacture(id));
    }

    @PostMapping("/{centreId}")
    public ResponseEntity<CentreFacture> addFacture(@PathVariable Long centreId, @RequestBody CentreFacture dto) {
        return ResponseEntity.ok(centrefactureService.saveFacture(centreId, dto));
    }

    @PutMapping("/{id}")
    public ResponseEntity<CentreFacture> updateFacture(@PathVariable Long id, @RequestBody CentreFacture updatedFacture) {
        return ResponseEntity.ok(centrefactureService.updateFacture(id, updatedFacture));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteFacture(@PathVariable Long id) {
        centrefactureService.deleteFacture(id);
        return ResponseEntity.noContent().build();
    }
}
