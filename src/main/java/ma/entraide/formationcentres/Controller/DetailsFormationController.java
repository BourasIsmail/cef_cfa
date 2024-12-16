package ma.entraide.formationcentres.Controller;

import ma.entraide.formationcentres.Entity.DetailFormation;
import ma.entraide.formationcentres.Entity.DetailsCentreActivite;
import ma.entraide.formationcentres.Service.DetailsFormationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin("*")
@RequestMapping("/DetailsFormation")
public class DetailsFormationController {
    @Autowired
    private DetailsFormationService detailsFormationService;

    @GetMapping("/all")
    public ResponseEntity<List<DetailFormation>> getAll(){
        List<DetailFormation> tab = detailsFormationService.getAll();
        return ResponseEntity.ok(tab);
    }

    @GetMapping("/{id}")
    public ResponseEntity<DetailFormation> getById(@PathVariable Long id) {
        try {
            DetailFormation d = detailsFormationService.getDetailFormation(id);
            return ResponseEntity.ok(d);
        } catch (Exception e) {
            return ResponseEntity.notFound().build();
        }
    }

    @PostMapping
    public ResponseEntity<DetailFormation> add(@RequestBody DetailFormation d) {
        try {
            DetailFormation d1 = detailsFormationService.saveDetailFormation(d);
            return ResponseEntity.ok(d1);
        } catch (Exception e) {
            return ResponseEntity.badRequest().build();
        }
    }

    @PutMapping
    public ResponseEntity<DetailFormation> update(@RequestBody DetailFormation d) {
        try {
            DetailFormation dca = detailsFormationService.updateDetailFormation(d);
            return ResponseEntity.ok(dca);
        } catch (Exception e) {
            return ResponseEntity.badRequest().build();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> delete(@PathVariable Long id) {
        try {
            detailsFormationService.deleteDetailFormation(id);
            return ResponseEntity.ok("deleted successfully");
        } catch (Exception e) {
            return ResponseEntity.badRequest().build();
        }
    }

}
