package ma.entraide.formationcentres.Repository;

import ma.entraide.formationcentres.Entity.Centre;


import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface CentreRepo extends JpaRepository<Centre, Long> {
	@Query("SELECT d FROM Centre d WHERE d.province.id = :id")
    List<Centre> findByCentreProvince(@Param("id") Long id);
}
