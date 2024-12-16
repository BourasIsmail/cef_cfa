package ma.entraide.formationcentres.Controller;

import ma.entraide.formationcentres.Entity.DetailsCentreActivite;
import ma.entraide.formationcentres.Entity.Filiere;
import ma.entraide.formationcentres.Service.FiliereService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin("*")
@RequestMapping("/filiere")
public class FiliereController {
    @Autowired
    private FiliereService filiereService;

    @GetMapping("/all")
    public ResponseEntity<List<Filiere>> getAll(){
        List<Filiere> tab = filiereService.findAll();
        return ResponseEntity.ok(tab);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Filiere> getById(@PathVariable Long id) {
        try {
            Filiere d = filiereService.findById(id);
            return ResponseEntity.ok(d);
        } catch (Exception e) {
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping("/typeActivite/{id}")
    public ResponseEntity<List<Filiere>> getByTypeActivite(@PathVariable Long id) {
        List<Filiere> tab = filiereService.findByTypeActivite(id);
        return ResponseEntity.ok(tab);
    }

    @PostMapping
    public ResponseEntity<Filiere> add(@RequestBody Filiere d) {
        try {
            Filiere d1 = filiereService.save(d);
            return ResponseEntity.ok(d1);
        } catch (Exception e) {
            return ResponseEntity.badRequest().build();
        }
    }

    @PutMapping
    public ResponseEntity<Filiere> update(@RequestBody Filiere d) {
        try {
            Filiere dca = filiereService.update(d);
            return ResponseEntity.ok(dca);
        } catch (Exception e) {
            return ResponseEntity.badRequest().build();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> delete(@PathVariable Long id) {
        try {
            filiereService.delete(id);
            return ResponseEntity.ok("Filiere deleted successfully");
        } catch (Exception e) {
            return ResponseEntity.badRequest().build();
        }
    }
}
