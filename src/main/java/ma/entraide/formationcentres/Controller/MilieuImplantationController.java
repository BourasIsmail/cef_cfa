package ma.entraide.formationcentres.Controller;

import ma.entraide.formationcentres.Entity.Filiere;
import ma.entraide.formationcentres.Entity.MilieuImplantation;
import ma.entraide.formationcentres.Service.MilieuImplantationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin("*")
@RequestMapping("/MilieuImplantation")
public class MilieuImplantationController {
    @Autowired
    private MilieuImplantationService milieuImplantationService;

    @GetMapping("/all")
    public ResponseEntity<List<MilieuImplantation>> getAll(){
        List<MilieuImplantation> tab = milieuImplantationService.getAllMilieuImplantation();
        return ResponseEntity.ok(tab);
    }

    @GetMapping("/{id}")
    public ResponseEntity<MilieuImplantation> getById(@PathVariable Long id) {
        try {
            MilieuImplantation d = milieuImplantationService.getMilieuImplantation(id);
            return ResponseEntity.ok(d);
        } catch (Exception e) {
            return ResponseEntity.notFound().build();
        }
    }


}
