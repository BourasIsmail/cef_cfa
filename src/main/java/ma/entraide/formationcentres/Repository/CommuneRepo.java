package ma.entraide.formationcentres.Repository;

import ma.entraide.formationcentres.Entity.Commune;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CommuneRepo extends JpaRepository<Commune, Integer> {
    @Query("select d from Commune d where d.province.id = :id")
    List<Commune> findByProvinceId(@Param("id") Long id);
}
