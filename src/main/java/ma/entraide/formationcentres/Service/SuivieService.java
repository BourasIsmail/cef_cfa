package ma.entraide.formationcentres.Service;

import ma.entraide.formationcentres.Entity.Activite;
import ma.entraide.formationcentres.Entity.Beneficiaire;
import ma.entraide.formationcentres.Entity.Centre;
import ma.entraide.formationcentres.Entity.Filiere;
import ma.entraide.formationcentres.Entity.Suivie;
import ma.entraide.formationcentres.Repository.ActiviteRepo;
import ma.entraide.formationcentres.Repository.BeneficiaireRepo;
import ma.entraide.formationcentres.Repository.CentreRepo;
import ma.entraide.formationcentres.Repository.FiliereRepo;
import ma.entraide.formationcentres.Repository.SuivieRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;
import java.util.List;

@Service
public class SuivieService {

    @Autowired
    private SuivieRepo suivieRepository;

    @Autowired
    private BeneficiaireRepo beneficiaireRepository;
    @Autowired
    private CentreRepo centreRepository;
    @Autowired
    private ActiviteRepo activiteRepository;

    @Autowired
    private FiliereRepo filiereRepository;
    public List<Suivie> getAllSuivies() {
        return suivieRepository.findAll();
    }

    public Suivie getSuivie(Long id) {
        return suivieRepository.findById(id)
            .orElseThrow(() -> new RuntimeException("Suivi non trouvé"));
    }
    public List<Suivie> getSuiviesByBeneficiaire(Long beneficiaireId) {
        return suivieRepository.findByBeneficiaireId(beneficiaireId);
    }
    public void deleteSuivie(Long id) {
        Suivie suivie = getSuivie(id);
        suivieRepository.delete(suivie);
    }

    public Suivie updateSuivie(Long id, Suivie updatedSuivie) {
    	
            Activite activite = activiteRepository.findById(updatedSuivie.getActivite().getId())
                .orElseThrow(() -> new RuntimeException("Activité non trouvée"));
            Beneficiaire beneficiaire = beneficiaireRepository.findById(updatedSuivie.getBeneficiaireId())
                    .orElseThrow(() -> new RuntimeException("Beneficiaire non trouvée"));
                
            Filiere filiere = filiereRepository.findById(updatedSuivie.getFiliere().getId())
                    .orElseThrow(() -> new RuntimeException("Filiere non trouvée"));
            Centre centre = centreRepository.findById(updatedSuivie.getCentre().getId())
                    .orElseThrow(() -> new RuntimeException("Centre non trouvée"));
            String typeActivite = activite.getTypeActivite().getName();
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
            LocalDate birthDate = LocalDate.parse(beneficiaire.getDateNaissance(), formatter);
            int age = (int) ChronoUnit.YEARS.between(birthDate, LocalDate.now());

            if (("CFA".equals(typeActivite) && age <= 30) || 
                (("CFA".equals(typeActivite) || "CEF".equals(typeActivite)) && age > 15)) {
            
        Suivie existingSuivie = getSuivie(id);
        existingSuivie.setBeneficiaireId(updatedSuivie.getBeneficiaireId());
        existingSuivie.setFiliere(filiere);
        existingSuivie.setActivite(activite);
        existingSuivie.setCentre(centre);
        existingSuivie.setEtatDeFormation(updatedSuivie.getEtatDeFormation());
        existingSuivie.setDateEffet(updatedSuivie.getDateEffet());
        existingSuivie.setObservation(updatedSuivie.getObservation());
        
        return suivieRepository.save(existingSuivie);
            } else {
                throw new IllegalArgumentException("Conditions non respectées pour cette activité.");
            }
    }

    public Suivie saveSuivie(Long beneficiaireId, Suivie dto) {
        Beneficiaire beneficiaire = beneficiaireRepository.findById(beneficiaireId)
            .orElseThrow(() -> new RuntimeException("Bénéficiaire non trouvé"));

        Activite activite = activiteRepository.findById(dto.getActivite().getId())
            .orElseThrow(() -> new RuntimeException("Activité non trouvée"));
        
        Filiere filiere = filiereRepository.findById(dto.getFiliere().getId())
                .orElseThrow(() -> new RuntimeException("Filiere non trouvée"));
        Centre centre = centreRepository.findById(dto.getCentre().getId())
                .orElseThrow(() -> new RuntimeException("Centre non trouvée"));
        String typeActivite = activite.getTypeActivite().getName();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        LocalDate birthDate = LocalDate.parse(beneficiaire.getDateNaissance(), formatter);
        int age = (int) ChronoUnit.YEARS.between(birthDate, LocalDate.now());

        if (("CFA".equals(typeActivite) && age <= 30) || 
            (("CFA".equals(typeActivite) || "CEF".equals(typeActivite)) && age > 15)) {
            
            Suivie suivie = new Suivie();
            suivie.setBeneficiaireId(beneficiaireId);
            suivie.setActivite(activite);
            suivie.setFiliere(filiere);
            suivie.setCentre(centre);
            suivie.setEtatDeFormation(dto.getEtatDeFormation());
            suivie.setDateEffet(dto.getDateEffet());
            suivie.setObservation(dto.getObservation());

            // 🔥 Ajouter à la liste des suivis du bénéficiaire
            beneficiaire.getSuivies().add(suivie);
            beneficiaireRepository.save(beneficiaire);

            return suivie;
            
        } else {
            throw new IllegalArgumentException("Conditions non respectées pour cette activité.");
        }
    }
}
