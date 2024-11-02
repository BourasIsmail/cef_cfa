package ma.entraide.formationcentres.Repository;

import ma.entraide.formationcentres.Entity.Personnel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PersonnelRepo extends JpaRepository<Personnel, Long> {
}
