package ma.entraide.formationcentres.Entity;



import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class BenefRequest {
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "beneficiaire_id", referencedColumnName = "id")
    private Beneficiaire beneficiaire;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "suivie_id", referencedColumnName = "id")
    private Suivie suivie;
}
