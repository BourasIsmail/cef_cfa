package ma.entraide.formationcentres.Controller;

import ma.entraide.formationcentres.Entity.Centre;
import ma.entraide.formationcentres.Entity.Province;
import ma.entraide.formationcentres.Service.CentreService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
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
    @GetMapping("/ByProvince/{id}")
    public ResponseEntity<List<Centre>> getCentreByProvinceId(@PathVariable Long id) {
        try {
            List<Centre> centres = centreService.getCentreByProvince(id);
            return ResponseEntity.ok(centres);
        } catch (Exception e){
            return ResponseEntity.notFound().build();
        }
    }
    @PostMapping("/add")
    public ResponseEntity<Centre> addCentre(@RequestBody Centre centre) {
        try {
            Centre newCentre = centreService.createCentre(centre);
            return ResponseEntity.ok(newCentre);
        } catch (Exception e) {
            return ResponseEntity.badRequest().build();
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<Centre> updateCentre(@PathVariable Long id,@RequestBody Centre centre) {
        try {
            Centre updatedCentre = centreService.updateCentre(id,centre);
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
