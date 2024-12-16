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

    @ManyToMany(cascade = {
            CascadeType.DETACH,
            CascadeType.MERGE,
            CascadeType.PERSIST,
            CascadeType.REFRESH
    })
    @JoinTable(
            name = "filiere_centre_activite",
            joinColumns = @JoinColumn(name = "centre_activite_id"),
            inverseJoinColumns = @JoinColumn(name = "filiere_id"))
    private List<Filiere> filieres;


}
