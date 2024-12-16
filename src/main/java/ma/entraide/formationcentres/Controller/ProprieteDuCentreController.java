package ma.entraide.formationcentres.Controller;

import ma.entraide.formationcentres.Entity.Personnel;
import ma.entraide.formationcentres.Entity.ProprieteDuCentre;
import ma.entraide.formationcentres.Service.ProprieteDuCentreService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin("*")
@RequestMapping("/ProprieteDuCentre")
public class ProprieteDuCentreController {
    @Autowired
    private ProprieteDuCentreService proprieteDuCentreService;

    @GetMapping("/all")
    public ResponseEntity<List<ProprieteDuCentre>> getAll(){
        List<ProprieteDuCentre> tab = proprieteDuCentreService.getProprieteDuCentre();
        return ResponseEntity.ok(tab);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ProprieteDuCentre> getById(@PathVariable Long id) {
        try {
            ProprieteDuCentre d = proprieteDuCentreService.getProprieteDuCentre(id);
            return ResponseEntity.ok(d);
        } catch (Exception e) {
            return ResponseEntity.notFound().build();
        }
    }

    @PostMapping
    public ResponseEntity<ProprieteDuCentre> add(@RequestBody ProprieteDuCentre d) {
        try {
            ProprieteDuCentre d1 = proprieteDuCentreService.add(d);
            return ResponseEntity.ok(d1);
        } catch (Exception e) {
            return ResponseEntity.badRequest().build();
        }
    }

    @PutMapping
    public ResponseEntity<ProprieteDuCentre> update(@RequestBody ProprieteDuCentre d) {
        try {
            ProprieteDuCentre dca = proprieteDuCentreService.update(d);
            return ResponseEntity.ok(dca);
        } catch (Exception e) {
            return ResponseEntity.badRequest().build();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> delete(@PathVariable Long id) {
        try {
            proprieteDuCentreService.delete(id);
            return ResponseEntity.ok("deleted successfully");
        } catch (Exception e) {
            return ResponseEntity.badRequest().build();
        }
    }
}
