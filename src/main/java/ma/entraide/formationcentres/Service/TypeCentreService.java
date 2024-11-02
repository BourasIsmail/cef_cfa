package ma.entraide.formationcentres.Service;

import ma.entraide.formationcentres.Entity.TypeCentre;
import ma.entraide.formationcentres.Repository.TypeCentreRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.rest.webmvc.ResourceNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class TypeCentreService {
    @Autowired
    private TypeCentreRepo typeCentreRepo;

    public List<TypeCentre> getAllTypeCentre() {
        return typeCentreRepo.findAll();
    }

    public TypeCentre getTypeCentreById(Long id) {
        Optional<TypeCentre> typeCentre = typeCentreRepo.findById(id);
        if (typeCentre.isPresent()) {
            return typeCentre.get();
        }
        else{
            throw new ResourceNotFoundException("Type Centre Not Found");
        }
    }

}
