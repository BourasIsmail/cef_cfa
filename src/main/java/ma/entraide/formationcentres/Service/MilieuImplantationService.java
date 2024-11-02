package ma.entraide.formationcentres.Service;

import ma.entraide.formationcentres.Entity.MilieuImplantation;
import ma.entraide.formationcentres.Repository.MilieuImplantationRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.rest.webmvc.ResourceNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class MilieuImplantationService {
    @Autowired
    private MilieuImplantationRepo milieuImplantationRepo;

    public List<MilieuImplantation> getAllMilieuImplantation() {
        return milieuImplantationRepo.findAll();
    }

    public MilieuImplantation getMilieuImplantation(Long id) {
        Optional<MilieuImplantation> milieuImplantation = milieuImplantationRepo.findById(id);
        if (milieuImplantation.isPresent()) {
            return milieuImplantation.get();
        }
        else {
            throw new ResourceNotFoundException("Milieu Implantation Not Found");
        }
    }
}
