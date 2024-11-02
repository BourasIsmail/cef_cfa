package ma.entraide.formationcentres.Repository;

import ma.entraide.formationcentres.Entity.MilieuImplantation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MilieuImplantationRepo extends JpaRepository<MilieuImplantation, Long> {
}
