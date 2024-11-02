package ma.entraide.formationcentres.Service;

import ma.entraide.formationcentres.Entity.Activite;
import ma.entraide.formationcentres.Entity.Personnel;
import ma.entraide.formationcentres.Entity.ProprieteDuCentre;
import ma.entraide.formationcentres.Entity.TypeActivite;
import ma.entraide.formationcentres.Repository.ActiviteRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.rest.webmvc.ResourceNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ActiviteService {
    @Autowired
    private ActiviteRepo activiteRepo;

    @Autowired
    private TypeActiviteService typeActiviteService;

    @Autowired
    private PersonnelService personnelService;

    @Autowired
    private ProprieteDuCentreService proprieteDuCentreService;

    public List<Activite> getAllActivites() {
        return activiteRepo.findAll();
    }

    public Activite getActiviteById(Long id) {
        Optional<Activite> activite = activiteRepo.findById(id);
        if (activite.isPresent()) {
            return activite.get();
        }
        else {
            throw new ResourceNotFoundException("Activite with id " + id + " not found");
        }
    }

    public Activite createActivite(Activite activite) {
        TypeActivite typeActivite = typeActiviteService.getTypeActiviteById(activite.getTypeActivite().getId());
        Personnel responsable = personnelService.getPersonnelById(activite.getResponsableActivite().getId());
        ProprieteDuCentre gestion = proprieteDuCentreService.getProprieteDuCentre(activite.getGestion().getId());

        activite.setTypeActivite(typeActivite);
        activite.setResponsableActivite(responsable);
        activite.setGestion(gestion);
        return activiteRepo.save(activite);
    }

    public Activite updateActivite(Activite activite) {
        Activite updatedActivite = getActiviteById(activite.getId());
        TypeActivite typeActivite = typeActiviteService.getTypeActiviteById(activite.getTypeActivite().getId());
        Personnel responsable = personnelService.getPersonnelById(activite.getResponsableActivite().getId());
        ProprieteDuCentre gestion = proprieteDuCentreService.getProprieteDuCentre(activite.getGestion().getId());
        activite.setTypeActivite(typeActivite);
        updatedActivite.setResponsableActivite(responsable);
        updatedActivite.setGestion(gestion);

        updatedActivite.setDateOuverture(activite.getDateOuverture());
        updatedActivite.setCapaciteAccueil(activite.getCapaciteAccueil());
        updatedActivite.setSuperficie(activite.getSuperficie());
        updatedActivite.setPartenariat(activite.getPartenariat());
        updatedActivite.setDateSignatureConvention(activite.getDateSignatureConvention());
        return activiteRepo.save(updatedActivite);
    }

    public void deleteActivite(Long id) {
        Optional<Activite> activite = activiteRepo.findById(id);
        if (activite.isPresent()) {
            activiteRepo.delete(activite.get());
        }
        else {
            throw new ResourceNotFoundException("Activite with id " + id + " not found");
        }
    }
}
