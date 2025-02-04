package ma.entraide.formationcentres.Repository;

import ma.entraide.formationcentres.Entity.Suivie;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface SuivieRepo extends JpaRepository<Suivie, Long> {
	@Query("SELECT s FROM Suivie s WHERE s.beneficiaire.id = :beneficiaireId")
    List<Suivie> findByBeneficiaireId(@Param("beneficiaireId") Long beneficiaireId);
}
