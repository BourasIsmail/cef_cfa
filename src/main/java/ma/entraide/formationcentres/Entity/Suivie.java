package ma.entraide.formationcentres.Entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Suivie {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(cascade = CascadeType.DETACH)
    @JoinColumn(name = "benef_id")
    private Beneficiaire beneficiaire;

    @ManyToOne(cascade = CascadeType.DETACH)
    @JoinColumn(name = "filiere_id")
    private Filiere filiere;

    private String etatDeFormation;

    private String dateEffet;

    private String observation;
}
