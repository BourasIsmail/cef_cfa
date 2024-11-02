package ma.entraide.formationcentres.Service;

import jakarta.persistence.EntityNotFoundException;
import ma.entraide.formationcentres.Entity.Beneficiaire;
import ma.entraide.formationcentres.Entity.Filiere;
import ma.entraide.formationcentres.Entity.Suivie;
import ma.entraide.formationcentres.Repository.SuivieRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class SuivieService {
    @Autowired
    private SuivieRepo suivieRepo;

    @Autowired
    private FiliereService filiereService;

    @Autowired
    private BeneficiaireService beneficiaireService;

    public List<Suivie> getAllSuivies() {
        return suivieRepo.findAll();
    }

    public Suivie getSuivie(Long id) {
        Optional<Suivie> suivie = suivieRepo.findById(id);
        if (suivie.isPresent()) {
            return suivie.get();
        }
        else{
            throw new EntityNotFoundException("Suivie n'existe pas");
        }
    }

    public Suivie saveSuivie(Suivie suivie) {
        Beneficiaire beneficiaire = beneficiaireService.getBeneficiaireById(suivie.getBeneficiaire().getId());
        Filiere filiere = filiereService.findById(suivie.getFiliere().getId());
        suivie.setFiliere(filiere);
        suivie.setBeneficiaire(beneficiaire);
        return suivieRepo.save(suivie);
    }

    public Suivie updateSuivie(Suivie suivie) {
        Suivie updatedSuivie = getSuivie(suivie.getId());
        Beneficiaire beneficiaire = beneficiaireService.getBeneficiaireById(suivie.getBeneficiaire().getId());
        Filiere filiere = filiereService.findById(suivie.getFiliere().getId());
        updatedSuivie.setFiliere(filiere);
        updatedSuivie.setBeneficiaire(beneficiaire);
        updatedSuivie.setEtatDeFormation(suivie.getEtatDeFormation());
        updatedSuivie.setDateEffet(suivie.getDateEffet());
        updatedSuivie.setObservation(suivie.getObservation());
        return suivieRepo.save(updatedSuivie);
    }

    public void deleteSuivie(Long id) {
        suivieRepo.deleteById(id);
    }
}
