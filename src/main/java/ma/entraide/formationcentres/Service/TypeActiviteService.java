package ma.entraide.formationcentres.Service;

import jakarta.persistence.EntityNotFoundException;
import ma.entraide.formationcentres.Entity.TypeActivite;
import ma.entraide.formationcentres.Repository.TypeActiviteRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class TypeActiviteService {
    @Autowired
    private TypeActiviteRepo typeActiviteRepo;

    public List<TypeActivite> getAllTypeActivites() {
        return typeActiviteRepo.findAll();
    }

    public TypeActivite getTypeActiviteById(Long id) {
        Optional<TypeActivite> typeActivite = typeActiviteRepo.findById(id);
        if(typeActivite.isPresent()) {
            return typeActivite.get();
        }
        else {
            throw new EntityNotFoundException("Type Activite Not Found");
        }
    }
}
