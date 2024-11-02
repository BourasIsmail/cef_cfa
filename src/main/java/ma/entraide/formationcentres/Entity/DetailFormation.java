package ma.entraide.formationcentres.Entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.cglib.core.Local;

import java.util.List;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class DetailFormation {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToMany(cascade = {
            CascadeType.DETACH,
            CascadeType.MERGE,
            CascadeType.PERSIST,
            CascadeType.REFRESH
    })
    @JoinTable(
            name = "beneficiaire_formation",
            joinColumns = @JoinColumn(name = "detailFormation"),
            inverseJoinColumns = @JoinColumn(name = "benef_id"))
    private List<Beneficiaire> beneficiaire;

    @ManyToOne(cascade = CascadeType.DETACH)
    @JoinColumn(name = "local_id")
    private Centre local;

    @ManyToOne(cascade = CascadeType.DETACH)
    @JoinColumn(name = "activite_id")
    private Activite activite;

    @ManyToOne(cascade = CascadeType.DETACH)
    @JoinColumn(name = "filiere_id")
    private Filiere filiere;
}
