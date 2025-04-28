package ma.entraide.formationcentres.Entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Filiere {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String filiere;

    private String secteur;

    private String specialite;

    @ManyToOne(cascade = CascadeType.DETACH)
    @JoinColumn(name = "type_id")
    private TypeActivite typeActivite;

	public Filiere(String specialite,String secteur,String filiere, TypeActivite typeActivite) {
		super();
		this.filiere = filiere;
        this.secteur = secteur;
        this.specialite = specialite;
		this.typeActivite = typeActivite;
	}
    
    
}
