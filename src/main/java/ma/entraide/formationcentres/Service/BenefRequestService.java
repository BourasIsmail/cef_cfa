package ma.entraide.formationcentres.Service;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import ma.entraide.formationcentres.Entity.Activite;
import ma.entraide.formationcentres.Entity.BenefRequest;
import ma.entraide.formationcentres.Entity.BenefRequestDTO;
import ma.entraide.formationcentres.Entity.Beneficiaire;
import ma.entraide.formationcentres.Entity.Centre;
import ma.entraide.formationcentres.Entity.Commune;
import ma.entraide.formationcentres.Entity.Filiere;
import ma.entraide.formationcentres.Entity.Province;
import ma.entraide.formationcentres.Entity.Suivie;
import ma.entraide.formationcentres.Repository.ActiviteRepo;
import ma.entraide.formationcentres.Repository.BenefRequestRepo;
import ma.entraide.formationcentres.Repository.CentreRepo;
import ma.entraide.formationcentres.Repository.CommuneRepo;
import ma.entraide.formationcentres.Repository.FiliereRepo;
import ma.entraide.formationcentres.Repository.ProvinceRepo;

@Service	
public class BenefRequestService {
	
	@Autowired
    private BenefRequestRepo benefRequestRepo;


    @Autowired
    private CommuneRepo communeRepo;

    @Autowired
    private ProvinceRepo provinceRepo;

    @Autowired
    private FiliereRepo filiereRepo;

    @Autowired
    private ActiviteRepo activiteRepo;

    @Autowired
    private CentreRepo centreRepo;
	public List<BenefRequest> getAllBenefRequest() {
        return benefRequestRepo.findAll();
    }
	@Transactional
    public BenefRequest createBenefRequest(BenefRequestDTO dto) {
        Beneficiaire beneficiaire = new Beneficiaire();
        beneficiaire.setNom(dto.getNom());
        beneficiaire.setPrenom(dto.getPrenom());
        beneficiaire.setAdresse(dto.getAdresse());
        beneficiaire.setTelephone(dto.getTelephone());
        beneficiaire.setDateNaissance(dto.getDateNaissance());
        beneficiaire.setSexe(dto.getSexe());
        beneficiaire.setCin(dto.getCin());

        Commune commune = communeRepo.findById(dto.getCommuneId())
                .orElseThrow(() -> new RuntimeException("Commune not found"));
        Province province = provinceRepo.findById(dto.getProvinceId())
                .orElseThrow(() -> new RuntimeException("Province not found"));

        beneficiaire.setCommune(commune);
        beneficiaire.setProvince(province);

        Suivie suivie = new Suivie();
        suivie.setBeneficiaire(beneficiaire);

        Filiere filiere = filiereRepo.findById(dto.getFiliereId())
                .orElseThrow(() -> new RuntimeException("Filiere not found"));
        Activite activite = activiteRepo.findById(dto.getActiviteId())
                .orElseThrow(() -> new RuntimeException("Activite not found"));
        Centre centre = centreRepo.findById(dto.getCentreId())
                .orElseThrow(() -> new RuntimeException("Centre not found"));

        suivie.setFiliere(filiere);
        String typeActivite = activite.getTypeActivite().getName();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        LocalDate birthDate = LocalDate.parse(dto.getDateNaissance(), formatter);

        // Calculate the age
        int age = (int) ChronoUnit.YEARS.between(birthDate, LocalDate.now());
        if("CFA".equals(typeActivite) && age <= 30) {
        	suivie.setActivite(activite);
        }
        if(("CFA".equals(typeActivite) || "CEF".equals(typeActivite) )&& age > 15) {
        	suivie.setActivite(activite);
        }
        suivie.setCentre(centre);
        suivie.setEtatDeFormation(dto.getEtatDeFormation());
        suivie.setDateEffet(dto.getDateEffet());
        suivie.setObservation(dto.getObservation());

        BenefRequest benefRequest = new BenefRequest();
        benefRequest.setBeneficiaire(beneficiaire);
        benefRequest.setSuivie(suivie);

        return benefRequestRepo.save(benefRequest);
    }

    public List<BenefRequest> getAllBenefRequests() {
        return benefRequestRepo.findAll();
    }

    public Optional<BenefRequest> getBenefRequestById(Long id) {
        return benefRequestRepo.findById(id);
    }

    public void deleteBenefRequest(Long id) {
        benefRequestRepo.deleteById(id);
    }
}


