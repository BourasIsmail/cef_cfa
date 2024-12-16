package ma.entraide.formationcentres.Repository;

import ma.entraide.formationcentres.Entity.Filiere;
import ma.entraide.formationcentres.Entity.Personnel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface FiliereRepo extends JpaRepository<Filiere, Long> {
    @Query("select f from Filiere f where f.typeActivite.id = :id")
    List<Filiere> findFiliereByTypeActivite(@Param("id") Long id);
}
