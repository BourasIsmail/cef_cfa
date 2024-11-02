package ma.entraide.formationcentres.Repository;

import ma.entraide.formationcentres.Entity.DetailsCentreActivite;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DetailsCentreActiviteRepo extends JpaRepository<DetailsCentreActivite, Long> {
}
