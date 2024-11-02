package ma.entraide.formationcentres.Repository;

import ma.entraide.formationcentres.Entity.TypeCentre;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TypeCentreRepo extends JpaRepository<TypeCentre, Long> {
}
