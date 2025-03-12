package ma.entraide.formationcentres.Repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import org.springframework.stereotype.Repository;
import ma.entraide.formationcentres.Entity.CentreFacture;
@Repository
public interface CentreFactureRepo extends JpaRepository<CentreFacture, Long> {
	List<CentreFacture> findByCentreId(Long centreId);
}
