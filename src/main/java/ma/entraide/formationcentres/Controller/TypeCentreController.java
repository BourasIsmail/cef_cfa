package ma.entraide.formationcentres.Controller;

import ma.entraide.formationcentres.Entity.TypeCentre;
import ma.entraide.formationcentres.Service.TypeCentreService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@CrossOrigin("*")
@RequestMapping("/typeCentre")
public class TypeCentreController {
    @Autowired
    private TypeCentreService typeCentreService;

    @GetMapping
    public ResponseEntity<List<TypeCentre>> getAllTypeCentre() {
        try{
            List<TypeCentre> typeCentres = typeCentreService.getAllTypeCentre();
            return ResponseEntity.ok(typeCentres);
        }
        catch(Exception e){
            e.printStackTrace();
            return ResponseEntity.noContent().build();
        }
    }
}
