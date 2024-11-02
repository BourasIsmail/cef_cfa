package ma.entraide.formationcentres.Repository;

import ma.entraide.formationcentres.Entity.Filiere;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface FiliereRepo extends JpaRepository<Filiere, Long> {
}
