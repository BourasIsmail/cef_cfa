package ma.entraide.formationcentres.Service;

import ma.entraide.formationcentres.Entity.Beneficiaire;
import ma.entraide.formationcentres.Entity.Commune;
import ma.entraide.formationcentres.Entity.Suivie;
import ma.entraide.formationcentres.Entity.Province;
import ma.entraide.formationcentres.Repository.BeneficiaireRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.rest.webmvc.ResourceNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class BeneficiaireService {
    @Autowired
    private BeneficiaireRepo beneficiaireRepo;

    @Autowired
    private CommuneService communeService;

    @Autowired
    private ProvinceService provinceService;

    public List<Beneficiaire> getAllBeneficiaire() {
        return beneficiaireRepo.findAll();
    }
    public List<Beneficiaire> getBeneficiaireByProvince(Long province) {
        return beneficiaireRepo.findByBeneficiaireProvince(province);
    }
    public Beneficiaire getBeneficiaireById(Long id) {
        Optional<Beneficiaire> beneficiaire = beneficiaireRepo.findById(id);
        if (beneficiaire.isPresent()) {
            return beneficiaire.get();
        }
        else {
            throw new ResourceNotFoundException("Beneficiaire id " + id + " n'existe pas");
        }
    }

    public Beneficiaire createBeneficiaire(Beneficiaire beneficiaire) {
        Commune commune = communeService.getCommuneById(beneficiaire.getCommune().getId());
        Province province = provinceService.getProvinceById(beneficiaire.getProvince().getId());
        beneficiaire.setProvince(province);
        beneficiaire.setCommune(commune);
     // Associer chaque suivi au bénéficiaire
        if (beneficiaire.getSuivies() != null) {
            for (Suivie suivie : beneficiaire.getSuivies()) {
                suivie.setBeneficiaire(beneficiaire);
            }
        }
        return beneficiaireRepo.save(beneficiaire);
    }

    public Beneficiaire updateBeneficiaire(Long id,Beneficiaire beneficiaire) {
        Beneficiaire updatedBenef = getBeneficiaireById(id);
        Commune commune = communeService.getCommuneById(beneficiaire.getCommune().getId());
        Province province = provinceService.getProvinceById(beneficiaire.getProvince().getId());
        updatedBenef.setProvince(province);
        updatedBenef.setCommune(commune);
        updatedBenef.setNom(beneficiaire.getNom());
        updatedBenef.setPrenom(beneficiaire.getPrenom());
        updatedBenef.setAdresse(beneficiaire.getAdresse());
        updatedBenef.setTelephone(beneficiaire.getTelephone());
        updatedBenef.setDateNaissance(beneficiaire.getDateNaissance());
        updatedBenef.setSexe(beneficiaire.getSexe());
        updatedBenef.setCin(beneficiaire.getCin());
        if (beneficiaire.getSuivies() != null) {
            for (Suivie suivie : beneficiaire.getSuivies()) {
                suivie.setBeneficiaire(updatedBenef); // Associer au bénéficiaire existant
                updatedBenef.getSuivies().add(suivie);
            }
        }
        return beneficiaireRepo.save(updatedBenef);
    }
    public Beneficiaire removeSuivieFromBeneficiaire(Long beneficiaireId, Long suivieId) {
        Beneficiaire beneficiaire = getBeneficiaireById(beneficiaireId);

        // Trouver la Suivie à supprimer
        Suivie suivieToRemove = beneficiaire.getSuivies().stream()
            .filter(suivie -> suivie.getId().equals(suivieId))
            .findFirst()
            .orElseThrow(() -> new RuntimeException("Suivie introuvable"));

        // Supprimer la Suivie de la liste
        beneficiaire.getSuivies().remove(suivieToRemove);

        // Sauvegarder les changements
        return beneficiaireRepo.save(beneficiaire);
    }

    public void deleteBeneficiaireById(Long id) {
        Beneficiaire deletedBenef = getBeneficiaireById(id);
        beneficiaireRepo.delete(deletedBenef);
    }
}
