package ma.entraide.formationcentres.Repository;

import ma.entraide.formationcentres.Entity.DetailFormation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DetailFormationRepo extends JpaRepository<DetailFormation, Long> {
}
