package ma.entraide.formationcentres.Service;

import ma.entraide.formationcentres.Entity.Beneficiaire;
import ma.entraide.formationcentres.Entity.Commune;
import ma.entraide.formationcentres.Entity.Province;
import ma.entraide.formationcentres.Entity.Suivie;
import ma.entraide.formationcentres.Repository.BeneficiaireRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public class BeneficiaireService {
    @Autowired
    private BeneficiaireRepo beneficiaireRepository;

    @Autowired
    private CommuneService communeService;

    @Autowired
    private ProvinceService provinceService;


    public List<Beneficiaire> getAllBeneficiaires() {
        return beneficiaireRepository.findAll();
    }

    public Beneficiaire getBeneficiaire(Long id) {
        return beneficiaireRepository.findById(id)
            .orElseThrow(() -> new RuntimeException("Bénéficiaire non trouvé"));
    }
    public List<Beneficiaire> getBeneficiaireByProvince(Long province) {
        return beneficiaireRepository.findByBeneficiaireProvince(province);
    }
    public Beneficiaire saveBeneficiaire(Beneficiaire beneficiaire, List<Suivie> suivies) {
    	Commune commune = communeService.getCommuneById(beneficiaire.getCommune().getId());
        Province province = provinceService.getProvinceById(beneficiaire.getProvince().getId());
        beneficiaire.setCommune(commune);
        beneficiaire.setProvince(province);
        for (Suivie suivie : suivies) {
            suivie.setBeneficiaireId(beneficiaire.getId());
        }
        beneficiaire.getSuivies().addAll(suivies);
        return beneficiaireRepository.save(beneficiaire);
    }

    public Beneficiaire updateBeneficiaire(Long id, Beneficiaire updatedBeneficiaire) {
    	Commune commune = communeService.getCommuneById(updatedBeneficiaire.getCommune().getId());
        Province province = provinceService.getProvinceById(updatedBeneficiaire.getProvince().getId());
        Beneficiaire existingBeneficiaire = getBeneficiaire(id);
        existingBeneficiaire.setNom(updatedBeneficiaire.getNom());
        existingBeneficiaire.setPrenom(updatedBeneficiaire.getPrenom());
        existingBeneficiaire.setAdresse(updatedBeneficiaire.getAdresse());
        existingBeneficiaire.setTelephone(updatedBeneficiaire.getTelephone());
        existingBeneficiaire.setDateNaissance(updatedBeneficiaire.getDateNaissance());
        existingBeneficiaire.setSexe(updatedBeneficiaire.getSexe());
        existingBeneficiaire.setCin(updatedBeneficiaire.getCin());
        existingBeneficiaire.setCommune(commune);
        existingBeneficiaire.setProvince(province);
        return beneficiaireRepository.save(existingBeneficiaire);
    }

    public void deleteBeneficiaire(Long id) {
        beneficiaireRepository.deleteById(id);
    }
}

