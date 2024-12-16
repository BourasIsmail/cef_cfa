package ma.entraide.formationcentres.Service;

import ma.entraide.formationcentres.Entity.Commune;
import ma.entraide.formationcentres.Entity.Personnel;
import ma.entraide.formationcentres.Entity.Province;
import ma.entraide.formationcentres.Repository.PersonnelRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.rest.webmvc.ResourceNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class PersonnelService {
    @Autowired
    private PersonnelRepo personnelRepo;

    @Autowired
    private CommuneService communeService;

    @Autowired
    private ProvinceService provinceService;

    public List<Personnel> getAllPersonnel() {
        return personnelRepo.findAll();
    }

    public Personnel getPersonnelById(Long id) {
        Optional<Personnel> personnel = personnelRepo.findById(id);
        if (personnel.isPresent()) {
            return personnel.get();
        }
        else {
            throw new ResourceNotFoundException("Personnel id " + id + " not found");
        }
    }

    public Personnel update(Personnel personnel) {
        Personnel newPersonnel = getPersonnelById(personnel.getId());
        Commune commune = communeService.getCommuneById(personnel.getCommune().getId());
        Province province = provinceService.getProvinceById(personnel.getProvince().getId());

        newPersonnel.setCommune(commune);
        newPersonnel.setProvince(province);
        newPersonnel.setNomComplet(personnel.getNomComplet());
        newPersonnel.setDiplome(personnel.getDiplome());
        newPersonnel.setGrade(personnel.getGrade());
        return personnelRepo.save(newPersonnel);
    }

    public Personnel save(Personnel personnel) {
        Commune commune = communeService.getCommuneById(personnel.getCommune().getId());
        Province province = provinceService.getProvinceById(personnel.getProvince().getId());
        personnel.setCommune(commune);
        personnel.setProvince(province);

        return personnelRepo.save(personnel);
    }

    public void deletePersonnel(Long id) {
        personnelRepo.deleteById(id);
    }

    public List<Personnel> getPersonnelByCommune(int id) {
        return personnelRepo.findByCommune(id);
    }

    public List<Personnel> getPersonnelByProvince(Long id) {
        return personnelRepo.findByProvince(id);
    }
}
