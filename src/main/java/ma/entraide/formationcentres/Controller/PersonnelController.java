package ma.entraide.formationcentres.Controller;

import ma.entraide.formationcentres.Entity.Filiere;
import ma.entraide.formationcentres.Entity.Personnel;
import ma.entraide.formationcentres.Service.PersonnelService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin("*")
@RequestMapping("/personnel")
public class PersonnelController {
    @Autowired
    private PersonnelService personnelService;

    @GetMapping("/all")
    public ResponseEntity<List<Personnel>> getAll(){
        List<Personnel> tab = personnelService.getAllPersonnel();
        return ResponseEntity.ok(tab);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Personnel> getById(@PathVariable Long id) {
        try {
            Personnel d = personnelService.getPersonnelById(id);
            return ResponseEntity.ok(d);
        } catch (Exception e) {
            return ResponseEntity.notFound().build();
        }
    }

    @PostMapping
    public ResponseEntity<Personnel> add(@RequestBody Personnel d) {
        try {
            Personnel d1 = personnelService.save(d);
            return ResponseEntity.ok(d1);
        } catch (Exception e) {
            return ResponseEntity.badRequest().build();
        }
    }

    @PutMapping
    public ResponseEntity<Personnel> update(@RequestBody Personnel d) {
        try {
            Personnel dca = personnelService.update(d);
            return ResponseEntity.ok(dca);
        } catch (Exception e) {
            return ResponseEntity.badRequest().build();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> delete(@PathVariable Long id) {
        try {
            personnelService.deletePersonnel(id);
            return ResponseEntity.ok("personnel deleted successfully");
        } catch (Exception e) {
            return ResponseEntity.badRequest().build();
        }
    }

    @GetMapping("/commune/{id}")
    public ResponseEntity<List<Personnel>> getCommune(@PathVariable int id) {
        try {
            List<Personnel> p = personnelService.getPersonnelByCommune(id);
            return ResponseEntity.ok(p);
        }catch(Exception e){
            return ResponseEntity.badRequest().build();
        }
    }

    @GetMapping("/province/{id}")
    public ResponseEntity<List<Personnel>> getProvince(@PathVariable Long id) {
        try{
            List<Personnel> p = personnelService.getPersonnelByProvince(id);
            return ResponseEntity.ok(p);
        }
        catch(Exception e){
            return ResponseEntity.badRequest().build();
        }
    }
}
