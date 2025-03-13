package ma.entraide.formationcentres.Entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Activite {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(cascade = CascadeType.DETACH)
    @JoinColumn(name = "type_id")
    private TypeActivite typeActivite;
    private String nom;
    private String dateOuverture;

    @ManyToOne(cascade = CascadeType.DETACH)
    @JoinColumn(name = "responsable_id")
    private Personnel responsableActivite;

    private long capaciteAccueil;

    private double superficie;

    @ManyToOne(cascade = CascadeType.DETACH)
    @JoinColumn(name = "gestion_id")
    private ProprieteDuCentre gestion;

    private String partenariat;

    private String dateSignatureConvention;

    @ManyToOne(cascade = CascadeType.DETACH)
    @JoinColumn(name = "centre_id")
    private Centre centre;

    @ManyToMany
    @JoinTable(
        name = "activite_filiere", // Join table name
        joinColumns = @JoinColumn(name = "activite_id"), // Activite side of the relation
        inverseJoinColumns = @JoinColumn(name = "filiere_id") // Filiere side of the relation
    )
    private List<Filiere> filieres;

	public Activite(TypeActivite typeActivite, String nom, String dateOuverture, Personnel responsableActivite,
			long capaciteAccueil, double superficie, ProprieteDuCentre gestion, String partenariat,
			String dateSignatureConvention, Centre centre, List<Filiere> filieres) {
		super();
		this.typeActivite = typeActivite;
		this.nom = nom;
		this.dateOuverture = dateOuverture;
		this.responsableActivite = responsableActivite;
		this.capaciteAccueil = capaciteAccueil;
		this.superficie = superficie;
		this.gestion = gestion;
		this.partenariat = partenariat;
		this.dateSignatureConvention = dateSignatureConvention;
		this.centre = centre;
		this.filieres = filieres;
	}
    
    

}
