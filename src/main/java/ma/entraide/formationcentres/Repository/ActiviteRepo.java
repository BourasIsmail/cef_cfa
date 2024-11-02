package ma.entraide.formationcentres.Repository;

import ma.entraide.formationcentres.Entity.Activite;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ActiviteRepo extends JpaRepository<Activite, Long> {
}
