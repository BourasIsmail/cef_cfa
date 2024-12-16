package ma.entraide.formationcentres.Controller;

import ma.entraide.formationcentres.Entity.Activite;
import ma.entraide.formationcentres.Service.ActiviteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin("*")
@RequestMapping("/activite")
public class ActiviteController {
    @Autowired
    private ActiviteService activiteService;

    @GetMapping("/all")
    public ResponseEntity<List<Activite>> getAllRegions() {
        List<Activite> activite = activiteService.getAllActivites();
        return ResponseEntity.ok(activite);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Activite> getActiviteById(@PathVariable Long id) {
        try {
            Activite activite = activiteService.getActiviteById(id);
            return ResponseEntity.ok(activite);
        } catch (Exception e) {
            return ResponseEntity.notFound().build();
        }
    }

    @PostMapping
    public ResponseEntity<Activite> addActivite(@RequestBody Activite activite) {
        try {
            Activite newActivite = activiteService.createActivite(activite);
            return ResponseEntity.ok(newActivite);
        } catch (Exception e) {
            return ResponseEntity.badRequest().build();
        }
    }

    @PutMapping
    public ResponseEntity<Activite> updateActivite(@RequestBody Activite activite) {
        try {
            Activite updatedActivite = activiteService.updateActivite(activite);
            return ResponseEntity.ok(updatedActivite);
        } catch (Exception e) {
            return ResponseEntity.badRequest().build();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteActivite(@PathVariable Long id) {
        try {
            activiteService.deleteActivite(id);
            return ResponseEntity.ok("Activite deleted successfully");
        } catch (Exception e) {
            return ResponseEntity.badRequest().build();
        }
    }
}
