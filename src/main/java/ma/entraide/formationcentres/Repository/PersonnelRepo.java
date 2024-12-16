package ma.entraide.formationcentres.Repository;

import ma.entraide.formationcentres.Entity.Personnel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PersonnelRepo extends JpaRepository<Personnel, Long> {
    @Query("select d from Personnel d where d.province.id = :id")
    List<Personnel> findByProvince(@Param("id") Long id);

    @Query("select d from Personnel d where d.commune.id = :id")
    List<Personnel> findByCommune(@Param("id") int id);
}
