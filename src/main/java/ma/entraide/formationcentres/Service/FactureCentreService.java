package ma.entraide.formationcentres.Service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.rest.webmvc.ResourceNotFoundException;
import org.springframework.stereotype.Service;

import ma.entraide.formationcentres.Entity.Centre;
import ma.entraide.formationcentres.Entity.CentreFacture;
import ma.entraide.formationcentres.Repository.CentreFactureRepo;


@Service
public class FactureCentreService {
	@Autowired
    private CentreService centreService;
	
	@Autowired
    private CentreFactureRepo centrefactureRepo;
	
	public List<CentreFacture> getFactures() {
        return centrefactureRepo.findAll();
    }
    public List<CentreFacture> getFactureByCentre(Long centre) {
        return centrefactureRepo.findFactureByCentre(centre);
    }
    public CentreFacture getFacture(Long id) {
        Optional<CentreFacture> centrefacture = centrefactureRepo.findById(id);
        if (centrefacture.isPresent()) {
            return centrefacture.get();
        }
        else {
            throw new ResourceNotFoundException("Facture with id " + id + " not found");
        }
    }

    public CentreFacture createFacture(CentreFacture centrefacture) {
        Centre centre = centreService.getCentre(centrefacture.getCentre().getId());        
        centrefacture.setCentre(centre);
        return centrefactureRepo.save(centrefacture);
    }

    public CentreFacture updateFacture(Long id,CentreFacture centrefacture) {
        CentreFacture updatedFacture = getFacture(id);
        Centre centre = centreService.getCentre(centrefacture.getCentre().getId());
        updatedFacture.setDatefacture(centrefacture.getDatefacture());
        updatedFacture.setEau(centrefacture.getEau());
        updatedFacture.setConsEau(centrefacture.getConsEau());
        updatedFacture.setElectricite(centrefacture.getElectricite());
        updatedFacture.setConsElect(centrefacture.getConsElect());
        updatedFacture.setTotal(centrefacture.getTotal());
        updatedFacture.setCentre(centre);
        return centrefactureRepo.save(updatedFacture);
    }
    
    public void deleteFacture(Long id) {
        centrefactureRepo.deleteById(id);
    }
}
