package ma.entraide.formationcentres.Service;

import ma.entraide.formationcentres.Entity.*;
import ma.entraide.formationcentres.Repository.DetailFormationRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.rest.webmvc.ResourceNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class DetailsFormationService {
    @Autowired
    private DetailFormationRepo detailFormationRepo;

    @Autowired
    private BeneficiaireService beneficiaireService;

    @Autowired
    private CentreService centreService;

    @Autowired
    private ActiviteService activiteService;

    @Autowired
    private FiliereService filiereService;

    public List<DetailFormation> getAll() {
        return detailFormationRepo.findAll();
    }

    public DetailFormation getDetailFormation(Long id) {
        Optional<DetailFormation> detailFormation = detailFormationRepo.findById(id);
        if (detailFormation.isPresent()) {
            return detailFormation.get();
        }
        else{
            throw new ResourceNotFoundException("Detail Formation with id " + id + " not found");
        }
    }

    public DetailFormation saveDetailFormation(DetailFormation detailFormation) {
        List<Beneficiaire> beneficiaires = new ArrayList<Beneficiaire>();
        for (Beneficiaire beneficiaire : detailFormation.getBeneficiaire()) {
            Beneficiaire benef = beneficiaireService.getBeneficiaireById(beneficiaire.getId());
            beneficiaires.add(benef);
        }
        detailFormation.setBeneficiaire(beneficiaires);

        Centre local = centreService.getCentre(detailFormation.getLocal().getId());
        detailFormation.setLocal(local);

        Activite activite = activiteService.getActiviteById(detailFormation.getActivite().getId());
        detailFormation.setActivite(activite);

        Filiere filiere = filiereService.findById(detailFormation.getFiliere().getId());
        detailFormation.setFiliere(filiere);

        return detailFormationRepo.save(detailFormation);
    }

    public DetailFormation updateDetailFormation(DetailFormation detailFormation) {
        DetailFormation detailFormation1 = getDetailFormation(detailFormation.getId());
        List<Beneficiaire> beneficiaires = new ArrayList<Beneficiaire>();
        for (Beneficiaire beneficiaire : detailFormation.getBeneficiaire()) {
            Beneficiaire benef = beneficiaireService.getBeneficiaireById(beneficiaire.getId());
            beneficiaires.add(benef);
        }
        detailFormation1.setBeneficiaire(beneficiaires);

        Centre local = centreService.getCentre(detailFormation.getLocal().getId());
        detailFormation1.setLocal(local);

        Activite activite = activiteService.getActiviteById(detailFormation.getActivite().getId());
        detailFormation1.setActivite(activite);

        Filiere filiere = filiereService.findById(detailFormation.getFiliere().getId());
        detailFormation1.setFiliere(filiere);

        return detailFormationRepo.save(detailFormation1);
    }

    public void deleteDetailFormation(Long id) {
        Optional<DetailFormation> detailFormation = detailFormationRepo.findById(id);
        if (detailFormation.isPresent()) {
            detailFormationRepo.delete(detailFormation.get());
        }
        else {
            throw new ResourceNotFoundException("Detail Formation with id " + id + " not found");
        }
    }
}
