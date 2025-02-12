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
    public Activite createActivite(Activite activite) {
        // Vérification et récupération des entités associées
        TypeActivite typeActivite = typeActiviteService
            .getTypeActiviteById(activite.getTypeActivite().getId());
        Personnel responsable = personnelService
            .getPersonnelById(activite.getResponsableActivite().getId());
        ProprieteDuCentre gestion = proprieteDuCentreService
            .getProprieteDuCentre(activite.getGestion().getId());

        // Mise à jour des relations
        activite.setTypeActivite(typeActivite);
        activite.setResponsableActivite(responsable);
        activite.setGestion(gestion);

        // Vérification et affectation des filières si elles existent
        List<Filiere> filieres = activite.getFilieres();
        List<Filiere> filieresNew = new ArrayList<>();
        for(Filiere filiere : filieres) {
        	filieresNew.add(filiereService.findById(filiere.getId()));
        	
        }
        activite.setFilieres(filieresNew);

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

        // Mise à jour des champs simples
        updatedActivite.setNom(activite.getNom());
        updatedActivite.setDateOuverture(activite.getDateOuverture());
        updatedActivite.setCapaciteAccueil(activite.getCapaciteAccueil());
        updatedActivite.setSuperficie(activite.getSuperficie());
        updatedActivite.setPartenariat(activite.getPartenariat());
        updatedActivite.setDateSignatureConvention(activite.getDateSignatureConvention());

        // Mise à jour des filières si une liste est fournie
        List<Filiere> filieres = activite.getFilieres();
        List<Filiere> filieresNew = new ArrayList<>();
        for(Filiere filiere : filieres) {
        	filieresNew.add(filiereService.findById(filiere.getId()));
        	
        }
        updatedActivite.setFilieres(filieresNew);

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
