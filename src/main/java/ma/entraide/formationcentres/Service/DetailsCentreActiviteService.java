package ma.entraide.formationcentres.Service;

import jakarta.persistence.EntityNotFoundException;
import ma.entraide.formationcentres.Entity.Activite;
import ma.entraide.formationcentres.Entity.Centre;
import ma.entraide.formationcentres.Entity.DetailsCentreActivite;
import ma.entraide.formationcentres.Entity.Filiere;
import ma.entraide.formationcentres.Repository.DetailsCentreActiviteRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class DetailsCentreActiviteService {
    @Autowired
    private CentreService centreService;

    @Autowired
    private ActiviteService activiteService;

    @Autowired
    private DetailsCentreActiviteRepo detailsCentreActiviteRepo;

    @Autowired
    private FiliereService filiereService;

    public DetailsCentreActivite findById(Long id) {
        Optional<DetailsCentreActivite> optional = detailsCentreActiviteRepo.findById(id);
        if (optional.isPresent()) {
            return optional.get();
        }
        else {
            throw new EntityNotFoundException();
        }
    }

    public List<DetailsCentreActivite> findAll() {
        return detailsCentreActiviteRepo.findAll();
    }

    public DetailsCentreActivite save(DetailsCentreActivite entity) {
        Centre centre = centreService.getCentre(entity.getCentre().getId());
        Activite activite = activiteService.getActiviteById(entity.getActivite().getId());
        List<Filiere> filieres = new ArrayList<>();
        for (Filiere filiere : entity.getFilieres()) {
            Filiere f = filiereService.findById(filiere.getId());
            filieres.add(f);
        }
        entity.setFilieres(filieres);
        entity.setCentre(centre);
        entity.setActivite(activite);
        return detailsCentreActiviteRepo.save(entity);
    }

    public DetailsCentreActivite update(DetailsCentreActivite entity) {
        DetailsCentreActivite detailsCentre = findById(entity.getId());
        Centre centre = centreService.getCentre(entity.getCentre().getId());
        Activite activite = activiteService.getActiviteById(entity.getActivite().getId());
        List<Filiere> filieres = new ArrayList<>();
        for (Filiere filiere : entity.getFilieres()) {
            Filiere f = filiereService.findById(filiere.getId());
            filieres.add(f);
        }
        detailsCentre.setFilieres(filieres);
        detailsCentre.setCentre(centre);
        detailsCentre.setActivite(activite);
        return detailsCentreActiviteRepo.save(detailsCentre);
    }

    public void delete(Long entity) {
        DetailsCentreActivite detailsCentre = findById(entity);
        detailsCentreActiviteRepo.delete(detailsCentre);
    }
}
