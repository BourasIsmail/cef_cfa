package ma.entraide.formationcentres.Controller;

import ma.entraide.formationcentres.Entity.Suivie;
import ma.entraide.formationcentres.Entity.TypeActivite;
import ma.entraide.formationcentres.Service.TypeActiviteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin("*")
@RequestMapping("/TypeActivite")
public class TypeActiviteController {
    @Autowired
    private TypeActiviteService typeActiviteService;

    @GetMapping("/all")
    public ResponseEntity<List<TypeActivite>> getAll(){
        List<TypeActivite> tab = typeActiviteService.getAllTypeActivites();
        return ResponseEntity.ok(tab);
    }

    @GetMapping("/{id}")
    public ResponseEntity<TypeActivite> getById(@PathVariable Long id) {
        try {
            TypeActivite d = typeActiviteService.getTypeActiviteById(id);
            return ResponseEntity.ok(d);
        } catch (Exception e) {
            return ResponseEntity.notFound().build();
        }
    }
}
