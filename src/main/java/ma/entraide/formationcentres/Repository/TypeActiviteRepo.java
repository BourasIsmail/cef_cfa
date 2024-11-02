package ma.entraide.formationcentres.Repository;

import ma.entraide.formationcentres.Entity.TypeActivite;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TypeActiviteRepo extends JpaRepository<TypeActivite, Long> {
}
