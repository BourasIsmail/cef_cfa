package ma.entraide.formationcentres.Repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import ma.entraide.formationcentres.Entity.CentreFacture;
@Repository
public interface CentreFactureRepo extends JpaRepository<CentreFacture, Long> {
	@Query("SELECT d FROM CentreFacture d WHERE d.centre.id = :id")
    List<CentreFacture> findFactureByCentre(@Param("id") Long id);
}
