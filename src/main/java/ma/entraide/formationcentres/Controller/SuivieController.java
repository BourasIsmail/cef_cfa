package ma.entraide.formationcentres.Controller;

import ma.entraide.formationcentres.Entity.Filiere;
import ma.entraide.formationcentres.Entity.Suivie;
import ma.entraide.formationcentres.Service.SuivieService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin("*")
@RequestMapping("/Suivie")
public class SuivieController {
    @Autowired
    private SuivieService suivieService;

    @GetMapping("/all")
    public ResponseEntity<List<Suivie>> getAll(){
        List<Suivie> tab = suivieService.getAllSuivies();
        return ResponseEntity.ok(tab);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Suivie> getById(@PathVariable Long id) {
        try {
            Suivie d = suivieService.getSuivie(id);
            return ResponseEntity.ok(d);
        } catch (Exception e) {
            return ResponseEntity.notFound().build();
        }
    }

    @PostMapping
    public ResponseEntity<Suivie> add(@RequestBody Suivie d) {
        try {
            Suivie d1 = suivieService.saveSuivie(d);
            return ResponseEntity.ok(d1);
        } catch (Exception e) {
            return ResponseEntity.badRequest().build();
        }
    }

    @PutMapping
    public ResponseEntity<Suivie> update(@RequestBody Suivie d) {
        try {
            Suivie dca = suivieService.updateSuivie(d);
            return ResponseEntity.ok(dca);
        } catch (Exception e) {
            return ResponseEntity.badRequest().build();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> delete(@PathVariable Long id) {
        try {
            suivieService.deleteSuivie(id);
            return ResponseEntity.ok("deleted successfully");
        } catch (Exception e) {
            return ResponseEntity.badRequest().build();
        }
    }
}
