package ma.entraide.formationcentres.Repository;

import ma.entraide.formationcentres.Entity.Activite;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface ActiviteRepo extends JpaRepository<Activite, Long> {
	
	@Query("select d from Activite d where d.centre.id = :id")
    List<Activite> findByCentreId(@Param("id") Long id);
	
}
