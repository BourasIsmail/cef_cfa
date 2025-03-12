package ma.entraide.formationcentres.Service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ma.entraide.formationcentres.Entity.Centre;
import ma.entraide.formationcentres.Entity.CentreFacture;
import ma.entraide.formationcentres.Repository.CentreFactureRepo;
import ma.entraide.formationcentres.Repository.CentreRepo;


@Service
public class FactureCentreService {
	
	
	@Autowired
    private CentreFactureRepo centrefactureRepo;
	@Autowired
    private CentreRepo centreRepository;
	
	public List<CentreFacture> getFactures() {
        return centrefactureRepo.findAll();
    }
	public List<CentreFacture> getFacturesByCentre(Long centreId) {
        return centrefactureRepo.findByCentreId(centreId);
    }
    public CentreFacture getFacture(Long id) {
        return centrefactureRepo.findById(id)
            .orElseThrow(() -> new RuntimeException("Suivi non trouvé"));
    }

    public CentreFacture createFacture(CentreFacture centrefacture) {
        return centrefactureRepo.save(centrefacture);
    }
    public CentreFacture saveFacture(Long centreId, CentreFacture dto) {
    	Centre centre = centreRepository.findById(centreId)
                .orElseThrow(() -> new RuntimeException("Centre non trouvée"));
    	CentreFacture facture = new CentreFacture();
    	facture.setCentreId(centreId);
    	facture.setDatefacture(dto.getDatefacture());
    	facture.setEau(dto.getEau());
    	facture.setConsEau(dto.getConsEau());
    	facture.setElectricite(dto.getElectricite());
    	facture.setConsElect(dto.getConsElect());
    	facture.setTotal(dto.getTotal());

        // 🔥 Ajouter à la liste des suivis du bénéficiaire
        centre.getFactures().add(facture);
        centreRepository.save(centre);

        return facture;
    	
    }
    public CentreFacture updateFacture(Long id,CentreFacture centrefacture) {
        CentreFacture updatedFacture = getFacture(id);
        updatedFacture.setCentreId(centrefacture.getCentreId());
        updatedFacture.setDatefacture(centrefacture.getDatefacture());
        updatedFacture.setEau(centrefacture.getEau());
        updatedFacture.setConsEau(centrefacture.getConsEau());
        updatedFacture.setElectricite(centrefacture.getElectricite());
        updatedFacture.setConsElect(centrefacture.getConsElect());
        updatedFacture.setTotal(centrefacture.getTotal());
        return centrefactureRepo.save(updatedFacture);
    }
    
    
    public void deleteFacture(Long id) {
        CentreFacture facture = getFacture(id);
        centrefactureRepo.delete(facture);
    }
}
