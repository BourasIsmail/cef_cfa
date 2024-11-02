package ma.entraide.formationcentres.Service;

import ma.entraide.formationcentres.Entity.*;
import ma.entraide.formationcentres.Repository.CentreRepo;
import ma.entraide.formationcentres.Repository.CommuneRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.rest.webmvc.ResourceNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CentreService {
    @Autowired
    private CentreRepo centreRepo;

    @Autowired
    private CommuneService communeService;

    @Autowired
    private ProvinceService provinceService;

    @Autowired
    private PersonnelService personnelService;

    @Autowired
    private MilieuImplantationService milieuService;

    public List<Centre> getCentres() {
        return centreRepo.findAll();
    }
    public Centre getCentre(Long id) {
        Optional<Centre> centre = centreRepo.findById(id);
        if (centre.isPresent()) {
            return centre.get();
        }
        else {
            throw new ResourceNotFoundException("Centre with id " + id + " not found");
        }
    }

    public Centre createCentre(Centre centre) {
        Commune commune = communeService.getCommuneById(centre.getCommune().getId());
        Province province = provinceService.getProvinceById(centre.getProvince().getId());
        Personnel responsable = personnelService.getPersonnelById(centre.getResponsable().getId());
        MilieuImplantation milieuImplantation = milieuService.getMilieuImplantation(centre.getMilieuImplantation().getId());
        centre.setCommune(commune);
        centre.setProvince(province);
        centre.setResponsable(responsable);
        centre.setMilieuImplantation(milieuImplantation);
        return centreRepo.save(centre);
    }

    public Centre updateCentre(Centre centre) {
        Centre updatedCentre = getCentre(centre.getId());
        Commune commune = communeService.getCommuneById(centre.getCommune().getId());
        Province province = provinceService.getProvinceById(centre.getProvince().getId());
        Personnel responsable = personnelService.getPersonnelById(centre.getResponsable().getId());
        MilieuImplantation milieuImplantation = milieuService.getMilieuImplantation(centre.getMilieuImplantation().getId());
        updatedCentre.setCommune(commune);
        updatedCentre.setProvince(province);
        updatedCentre.setResponsable(responsable);
        updatedCentre.setMilieuImplantation(milieuImplantation);
        updatedCentre.setNomFr(centre.getNomFr());
        updatedCentre.setNomAr(centre.getNomAr());
        updatedCentre.setDateConstruction(centre.getDateConstruction());
        updatedCentre.setTelephone(centre.getTelephone());
        updatedCentre.setEtat(centre.getEtat());
        updatedCentre.setAdresse(centre.getAdresse());
        updatedCentre.setSuperficie(centre.getSuperficie());
        updatedCentre.setUtilisation(centre.getUtilisation());
        updatedCentre.setElectricite(centre.getElectricite());
        updatedCentre.setTelephoneFixe(centre.getTelephoneFixe());
        updatedCentre.setInternet(centre.getInternet());
        updatedCentre.setNbrPC(centre.getNbrPC());
        updatedCentre.setNbrImprimante(centre.getNbrImprimante());
        updatedCentre.setNbrPersonneConnaissanceInfo(centre.getNbrPersonneConnaissanceInfo());
        updatedCentre.setNbrPersonneOperationelApresFormation(centre.getNbrPersonneOperationelApresFormation());
        updatedCentre.setCoutEstimationAmenagement(centre.getCoutEstimationAmenagement());
        updatedCentre.setCoutEstimationEquipement(centre.getCoutEstimationEquipement());
        updatedCentre.setObservation(centre.getObservation());
        return centreRepo.save(updatedCentre);
    }

    public void deleteCentre(Long id) {
        centreRepo.deleteById(id);
    }
}
