package ma.entraide.formationcentres.Service;

import ma.entraide.formationcentres.Entity.Filiere;
import ma.entraide.formationcentres.Entity.TypeActivite;
import ma.entraide.formationcentres.Repository.FiliereRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.rest.webmvc.ResourceNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class FiliereService {
    @Autowired
    private FiliereRepo filiereRepo;

    @Autowired
    private TypeActiviteService typeActiviteService;


    public List<Filiere> findAll() {
        return filiereRepo.findAll();
    }

    public Filiere findById(Long id) {
        Optional<Filiere> filiere = filiereRepo.findById(id);
        if (filiere.isPresent()) {
            return filiere.get();
        }
        else  {
            throw new ResourceNotFoundException("Filiere " + id + " not found");
        }
    }

    public Filiere save(Filiere filiere) {
        TypeActivite typeActivite = typeActiviteService.getTypeActiviteById(filiere.getTypeActivite().getId());
        filiere.setTypeActivite(typeActivite);
        return filiereRepo.save(filiere);
    }

    public Filiere update(Filiere filiere) {
        Filiere updatedFiliere = findById(filiere.getId());
        TypeActivite typeActivite = typeActiviteService.getTypeActiviteById(filiere.getTypeActivite().getId());
        updatedFiliere.setTypeActivite(typeActivite);
        updatedFiliere.setNom(filiere.getNom());
        return filiereRepo.save(updatedFiliere);
    }

    public void delete(Long id) {
        filiereRepo.deleteById(id);
    }

    public List<Filiere> findByTypeActivite(Long id) {
        return filiereRepo.findFiliereByTypeActivite(id);
    }
}
