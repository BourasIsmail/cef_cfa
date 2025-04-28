package ma.entraide.formationcentres.Entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Personnel {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "personnel_id")
    private Long id;

    private String nomComplet;

    private String telephone;

    private String email;

    private String cin;

    private String fonction;

    private boolean vacataire;

    private String niveauEtude;

    @ManyToOne(cascade = CascadeType.DETACH)
    @JoinColumn(name = "province_id")
    private Province province;

    @ManyToOne(cascade = CascadeType.DETACH)
    @JoinColumn(name = "commune_id")
    private Commune commune;


}
