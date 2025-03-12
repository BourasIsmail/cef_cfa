package ma.entraide.formationcentres.Repository;

import ma.entraide.formationcentres.Entity.Suivie;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SuivieRepo extends JpaRepository<Suivie, Long> {
    List<Suivie> findByBeneficiaireId(Long beneficiaireId);

}
