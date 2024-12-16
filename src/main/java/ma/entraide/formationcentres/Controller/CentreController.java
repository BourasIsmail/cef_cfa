package ma.entraide.formationcentres.Controller;

import ma.entraide.formationcentres.Entity.Centre;
import ma.entraide.formationcentres.Service.CentreService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin("*")
@RequestMapping("/centre")
public class CentreController {
    @Autowired
    private CentreService centreService;

    @GetMapping("/all")
    public ResponseEntity<List<Centre>> getAllCentres() {
        List<Centre> centres = centreService.getCentres();
        return ResponseEntity.ok(centres);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Centre> getCentreById(@PathVariable Long id) {
        try {
            Centre centre = centreService.getCentre(id);
            return ResponseEntity.ok(centre);
        } catch (Exception e) {
            return ResponseEntity.notFound().build();
        }
    }

    @PostMapping
    public ResponseEntity<Centre> addCentre(@RequestBody Centre centre) {
        try {
            Centre newCentre = centreService.createCentre(centre);
            return ResponseEntity.ok(newCentre);
        } catch (Exception e) {
            return ResponseEntity.badRequest().build();
        }
    }

    @PutMapping
    public ResponseEntity<Centre> updateCentre(@RequestBody Centre centre) {
        try {
            Centre updatedCentre = centreService.updateCentre(centre);
            return ResponseEntity.ok(updatedCentre);
        } catch (Exception e) {
            return ResponseEntity.badRequest().build();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteCentre(@PathVariable Long id) {
        try {
            centreService.deleteCentre(id);
            return ResponseEntity.ok("Centre deleted successfully");
        } catch (Exception e) {
            return ResponseEntity.badRequest().build();
        }
    }
}
