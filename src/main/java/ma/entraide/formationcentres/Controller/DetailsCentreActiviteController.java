package ma.entraide.formationcentres.Controller;

import ma.entraide.formationcentres.Entity.Centre;
import ma.entraide.formationcentres.Entity.Commune;
import ma.entraide.formationcentres.Entity.DetailsCentreActivite;
import ma.entraide.formationcentres.Service.DetailsCentreActiviteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin("*")
@RequestMapping("/DetailsCentreActivite")
public class DetailsCentreActiviteController {
    @Autowired
    private DetailsCentreActiviteService detailsCentreActiviteService;

    @GetMapping("/all")
    public ResponseEntity<List<DetailsCentreActivite>> getAll(){
        List<DetailsCentreActivite> tab = detailsCentreActiviteService.findAll();
        return ResponseEntity.ok(tab);
    }

    @GetMapping("/{id}")
    public ResponseEntity<DetailsCentreActivite> getById(@PathVariable Long id) {
        try {
            DetailsCentreActivite d = detailsCentreActiviteService.findById(id);
            return ResponseEntity.ok(d);
        } catch (Exception e) {
            return ResponseEntity.notFound().build();
        }
    }

    @PostMapping
    public ResponseEntity<DetailsCentreActivite> add(@RequestBody DetailsCentreActivite d) {
        try {
            DetailsCentreActivite d1 = detailsCentreActiviteService.save(d);
            return ResponseEntity.ok(d1);
        } catch (Exception e) {
            return ResponseEntity.badRequest().build();
        }
    }

    @PutMapping
    public ResponseEntity<DetailsCentreActivite> update(@RequestBody DetailsCentreActivite d) {
        try {
            DetailsCentreActivite dca = detailsCentreActiviteService.update(d);
            return ResponseEntity.ok(dca);
        } catch (Exception e) {
            return ResponseEntity.badRequest().build();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> delete(@PathVariable Long id) {
        try {
            detailsCentreActiviteService.delete(id);
            return ResponseEntity.ok("detailsCentreActivite deleted successfully");
        } catch (Exception e) {
            return ResponseEntity.badRequest().build();
        }
    }
}
