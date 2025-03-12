package ma.entraide.formationcentres.Repository;

import ma.entraide.formationcentres.Entity.Beneficiaire;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface BeneficiaireRepo extends JpaRepository<Beneficiaire, Long> {
	@Query("SELECT d FROM Beneficiaire d WHERE d.province.id = :id")
    List<Beneficiaire> findByBeneficiaireProvince(@Param("id") Long id);
}
