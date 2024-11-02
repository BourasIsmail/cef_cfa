package ma.entraide.formationcentres.Repository;

import ma.entraide.formationcentres.Entity.Suivie;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SuivieRepo extends JpaRepository<Suivie, Long> {
}
