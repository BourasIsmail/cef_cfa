package ma.entraide.formationcentres.Service;

import ma.entraide.formationcentres.Entity.Activite;
import ma.entraide.formationcentres.Entity.Centre;
import ma.entraide.formationcentres.Entity.Commune;
import ma.entraide.formationcentres.Entity.Filiere;
import ma.entraide.formationcentres.Entity.Personnel;
import ma.entraide.formationcentres.Entity.ProprieteDuCentre;
import ma.entraide.formationcentres.Entity.TypeActivite;
import ma.entraide.formationcentres.Repository.ActiviteRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.rest.webmvc.ResourceNotFoundException;
import org.springframework.stereotype.Service;

import jakarta.persistence.EntityNotFoundException;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class ActiviteService {
    @Autowired
    private ActiviteRepo activiteRepo;

    @Autowired
    private TypeActiviteService typeActiviteService;

    @Autowired
    private PersonnelService personnelService;
    @Autowired
    private FiliereService filiereService;
    @Autowired
    private CentreService centreService;
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
    public List<Activite> getActiviteByCentre(Long id) {
        return activiteRepo.findByCentreId(id);
    }
    public Activite saveActivite(Activite activite) {
        // Vérification et récupération des entités associées
        TypeActivite typeActivite = typeActiviteService
            .getTypeActiviteById(activite.getTypeActivite().getId());
        Personnel responsable = personnelService
            .getPersonnelById(activite.getResponsableActivite().getId());
        ProprieteDuCentre gestion = proprieteDuCentreService
            .getProprieteDuCentre(activite.getGestion().getId());
        Centre centre = centreService
                .getCentre(activite.getCentre().getId());
        // Mise à jour des relations
        
        activite.setTypeActivite(typeActivite);
        activite.setResponsableActivite(responsable);
        activite.setGestion(gestion);
        activite.setCentre(centre);
        List<Filiere> filieres = activite.getFilieres();
        List<Filiere> filieresNew = new ArrayList<>();
        for(Filiere filiere : filieres) {
            Filiere existingFiliere = filiereService.findById(filiere.getId());
            if (existingFiliere != null) {
                filieresNew.add(existingFiliere);
            }
        }

        activite.setFilieres(filieresNew);

        List<Personnel> personnels = activite.getPersonnels();
        List<Personnel> personnelsNew = new ArrayList<>();
        for(Personnel personnel : personnels) {
            personnelsNew.add(personnelService.getPersonnelById(personnel.getId()));
        }
        activite.setPersonnels(personnelsNew);

        return activiteRepo.save(activite);
    }

    public Activite updateActivite(Long id, Activite activite) {
        Activite updatedActivite = activiteRepo.findById(id)
            .orElseThrow(() -> new EntityNotFoundException("Activité non trouvée"));

        // Mise à jour des relations si elles sont fournies
        if (activite.getTypeActivite() != null) {
            TypeActivite typeActivite = typeActiviteService
                .getTypeActiviteById(activite.getTypeActivite().getId());
            updatedActivite.setTypeActivite(typeActivite);
        }

        if (activite.getResponsableActivite() != null) {
            Personnel responsable = personnelService
                .getPersonnelById(activite.getResponsableActivite().getId());
            updatedActivite.setResponsableActivite(responsable);
        }

        if (activite.getGestion() != null) {
            ProprieteDuCentre gestion = proprieteDuCentreService
                .getProprieteDuCentre(activite.getGestion().getId());
            updatedActivite.setGestion(gestion);
        }
        if (activite.getCentre() != null) {
            Centre centre = centreService
                .getCentre(activite.getCentre().getId());
            updatedActivite.setCentre(centre);
        }
        // Mise à jour des champs simples
        updatedActivite.setDateOuverture(activite.getDateOuverture());

        // Mise à jour des filières si une liste est fournie
        List<Filiere> filieres = activite.getFilieres();
        List<Filiere> filieresNew = new ArrayList<>();
        for(Filiere filiere : filieres) {
        	filieresNew.add(filiereService.findById(filiere.getId()));
        	
        }
        updatedActivite.setFilieres(filieresNew);

        List<Personnel> personnels = activite.getPersonnels();
        List<Personnel> personnelsNew = new ArrayList<>();
        for(Personnel personnel : personnels) {
            personnelsNew.add(personnelService.getPersonnelById(personnel.getId()));
        }
        updatedActivite.setPersonnels(personnelsNew);
        updatedActivite.setPartenariat(activite.isPartenariat());

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
