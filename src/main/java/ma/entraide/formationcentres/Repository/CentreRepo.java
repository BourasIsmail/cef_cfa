package ma.entraide.formationcentres.Repository;

import ma.entraide.formationcentres.Entity.Centre;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CentreRepo extends JpaRepository<Centre, Long> {
}
