package ma.entraide.formationcentres.Entity;

import com.fasterxml.jackson.annotation.JsonManagedReference;

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
    @JsonManagedReference
    @ManyToOne(cascade = CascadeType.DETACH)
    @JoinColumn(name = "benef_id")
    private Beneficiaire beneficiaire;

    @ManyToOne(cascade = CascadeType.DETACH)
    @JoinColumn(name = "filiere_id")
    private Filiere filiere;
    
    @ManyToOne(cascade = CascadeType.DETACH)
    @JoinColumn(name = "activite_id")
    private Activite activite;
    
    @ManyToOne(cascade = CascadeType.DETACH)
    @JoinColumn(name = "centre_id")
    private Centre centre;
    
    private String etatDeFormation;

    private String dateEffet;

    private String observation;
}
