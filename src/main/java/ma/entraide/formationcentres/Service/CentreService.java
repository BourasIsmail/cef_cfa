package ma.entraide.formationcentres.Service;

import ma.entraide.formationcentres.Entity.*;
import ma.entraide.formationcentres.Repository.CentreRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

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
    public List<Centre> getCentreByProvince(Long province) {
        return centreRepo.findByCentreProvince(province);
    }
    
    public Centre getCentre(Long id) {
        return centreRepo.findById(id)
            .orElseThrow(() -> new RuntimeException("centre non trouvé"));
    }
    public Centre saveCentre(Centre centre) {
        Commune commune = communeService.getCommuneById(centre.getCommune().getId());
        Province province = provinceService.getProvinceById(centre.getProvince().getId());
        Personnel responsable = personnelService.getPersonnelById(centre.getResponsable().getId());
        MilieuImplantation milieuImplantation = milieuService.getMilieuImplantation(centre.getMilieuImplantation().getId());
        centre.setCommune(commune);
        centre.setProvince(province);
        centre.setResponsable(responsable);
        centre.setMilieuImplantation(milieuImplantation);
        if("loye".equals(centre.getPossession())) {
        	centre.setMontantAllocation(centre.getMontantAllocation());
        }else {
        	centre.setMontantAllocation(0);
        }
        
        return centreRepo.save(centre);
    }

    public Centre updateCentre(Long id,Centre centre) {
        Centre updatedCentre = getCentre(id);
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
        updatedCentre.setAdresse(centre.getAdresse());
        updatedCentre.setSuperficie(centre.getSuperficie());
        updatedCentre.setElectricite(centre.getElectricite());
        updatedCentre.setTelephoneFixe(centre.getTelephoneFixe());
        updatedCentre.setInternet(centre.getInternet());
        updatedCentre.setPossession(centre.getPossession());
        updatedCentre.setNaturePropriete(centre.getNaturePropriete());
        if("loye".equals(centre.getPossession())) {
        	updatedCentre.setMontantAllocation(centre.getMontantAllocation());
        }else {
        	updatedCentre.setMontantAllocation(0);;
        }
        updatedCentre.setNbrPC(centre.getNbrPC());
        updatedCentre.setNbrImprimante(centre.getNbrImprimante());
        updatedCentre.setNbrPersonneConnaissanceInfo(centre.getNbrPersonneConnaissanceInfo());
        updatedCentre.setNbrPersonneOperationelApresFormation(centre.getNbrPersonneOperationelApresFormation());
        updatedCentre.setCoutEstimationEquipement(centre.getCoutEstimationEquipement());
        updatedCentre.setLatitude(centre.getLatitude());
        updatedCentre.setLongitude(centre.getLongitude());
        
        return centreRepo.save(updatedCentre);
    }
    
    public void deleteCentre(Long id) {
        centreRepo.deleteById(id);
    }
}
