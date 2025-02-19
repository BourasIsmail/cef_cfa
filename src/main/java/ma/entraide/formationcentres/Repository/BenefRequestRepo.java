package ma.entraide.formationcentres.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import ma.entraide.formationcentres.Entity.BenefRequest;

@Repository
public interface BenefRequestRepo extends JpaRepository<BenefRequest, Long>{

}
