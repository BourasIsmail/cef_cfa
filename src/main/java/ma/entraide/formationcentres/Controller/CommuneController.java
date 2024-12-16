package ma.entraide.formationcentres.Controller;

import ma.entraide.formationcentres.Entity.Commune;
import ma.entraide.formationcentres.Service.CommuneService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin("*")
@RequestMapping("/commune")
public class CommuneController {
    @Autowired
    private CommuneService communeService;

    @GetMapping("/all")
    public ResponseEntity<List<Commune>> getAllCommunes() {
        List<Commune> communes = communeService.getAllCommunes();
        return ResponseEntity.ok(communes);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Commune> getCommuneById(@PathVariable int id) {
        try {
            Commune commune = communeService.getCommuneById(id);
            return ResponseEntity.ok(commune);
        } catch (Exception e) {
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping("/byProvince/{id}")
    public ResponseEntity<List<Commune>> getCommuneByProvince(@PathVariable Long id) {
        List<Commune> communes = communeService.getCommuneByProvince(id);
        return ResponseEntity.ok(communes);
    }


}
