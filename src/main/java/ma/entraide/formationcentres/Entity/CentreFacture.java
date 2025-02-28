package ma.entraide.formationcentres.Entity;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class CentreFacture {
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
	
	private String datefacture;
	
	private double eau;
	
	private double consEau ;
	
	private double electricite;
	
	private double consElect;
	
	private double total;
	
	@ManyToOne(cascade = CascadeType.DETACH)
    @JoinColumn(name = "centre_id")
    private Centre centre;
	
	
}
