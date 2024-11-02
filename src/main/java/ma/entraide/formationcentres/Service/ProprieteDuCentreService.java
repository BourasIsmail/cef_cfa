package ma.entraide.formationcentres.Service;

import ma.entraide.formationcentres.Entity.ProprieteDuCentre;
import ma.entraide.formationcentres.Repository.ProprieteDuCentreRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.rest.webmvc.ResourceNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ProprieteDuCentreService {
    @Autowired
    private ProprieteDuCentreRepo proprieteDuCentreRepo;

    public List<ProprieteDuCentre> getProprieteDuCentre() {
        return proprieteDuCentreRepo.findAll();
    }

    public ProprieteDuCentre getProprieteDuCentre(Long id) {
        Optional<ProprieteDuCentre> proprieteDuCentre = proprieteDuCentreRepo.findById(id);
        if (proprieteDuCentre.isPresent()) {
            return proprieteDuCentre.get();
        }
        throw new ResourceNotFoundException("Propriete Du Centre Not Found");
    }

    public ProprieteDuCentre add(ProprieteDuCentre proprieteDuCentre) {
        return proprieteDuCentreRepo.save(proprieteDuCentre);
    }

    public ProprieteDuCentre update(ProprieteDuCentre proprieteDuCentre) {
        ProprieteDuCentre proprieteDuCentre1 = getProprieteDuCentre(proprieteDuCentre.getId());
        proprieteDuCentre1.setNom(proprieteDuCentre.getNom());
        proprieteDuCentre1.setAutre(proprieteDuCentre.getAutre());
        return proprieteDuCentreRepo.save(proprieteDuCentre1);
    }

    public void delete(Long id){
        proprieteDuCentreRepo.deleteById(id);
    }
}
