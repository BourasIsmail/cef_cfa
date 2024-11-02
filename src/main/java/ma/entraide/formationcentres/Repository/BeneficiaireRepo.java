package ma.entraide.formationcentres.Repository;

import ma.entraide.formationcentres.Entity.Beneficiaire;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BeneficiaireRepo extends JpaRepository<Beneficiaire, Long> {
}
