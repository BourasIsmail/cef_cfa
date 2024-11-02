package ma.entraide.formationcentres.Repository;

import ma.entraide.formationcentres.Entity.ProprieteDuCentre;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProprieteDuCentreRepo extends JpaRepository<ProprieteDuCentre, Long> {
}
