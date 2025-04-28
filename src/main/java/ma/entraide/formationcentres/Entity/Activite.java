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

    private String dateOuverture;

    @ManyToOne(cascade = CascadeType.DETACH)
    @JoinColumn(name = "responsable_id")
    private Personnel responsableActivite;


    @ManyToOne(cascade = CascadeType.DETACH)
    @JoinColumn(name = "gestion_id")
    private ProprieteDuCentre gestion;


    @ManyToOne(cascade = CascadeType.DETACH)
    @JoinColumn(name = "centre_id")
    private Centre centre;

    private boolean partenariat;

    @ManyToMany
    @JoinTable(
        name = "activite_filiere", // Join table name
        joinColumns = @JoinColumn(name = "activite_id"), // Activite side of the relation
        inverseJoinColumns = @JoinColumn(name = "filiere_id") // Filiere side of the relation
    )
    private List<Filiere> filieres;

    @ManyToMany
    @JoinTable(
            name = "activite_personnel",
            joinColumns = @JoinColumn(name = "activite_id"),
            inverseJoinColumns = @JoinColumn(name = "personnel_id")
    )
    private List<Personnel> personnels;

	public Activite(TypeActivite typeActivite, String dateOuverture, Personnel responsableActivite,
                    ProprieteDuCentre gestion,boolean partenariat, Centre centre, List<Filiere> filieres, List<Personnel> personnels) {
		super();
		this.typeActivite = typeActivite;
		this.dateOuverture = dateOuverture;
        this.partenariat = partenariat;
		this.responsableActivite = responsableActivite;
		this.gestion = gestion;
		this.centre = centre;
		this.filieres = filieres;
        this.personnels = personnels;
	}


    
    

}
