package ma.entraide.formationcentres.Service;

import ma.entraide.formationcentres.Entity.Commune;
import ma.entraide.formationcentres.Repository.CommuneRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.rest.webmvc.ResourceNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CommuneService {
    @Autowired
    private CommuneRepo communeRepo;

    public List<Commune> getAllCommunes() {
        return communeRepo.findAll();
    }

    public Commune getCommuneById(int id) {
        Optional<Commune> commune = communeRepo.findById(id);
        if (commune.isPresent()) {
            return commune.get();
        }
        else {
            throw new ResourceNotFoundException("No commune found with id " + id);
        }
    }

    public List<Commune> getCommuneByProvince(Long id) {
        return communeRepo.findByProvinceId(id);
    }

}
