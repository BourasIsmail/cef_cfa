package ma.entraide.formationcentres.Entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Centre {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String nomFr;

    private String nomAr;

    @ManyToOne(cascade = CascadeType.DETACH)
    @JoinColumn(name = "typeCentre_id")
    private TypeCentre typeCentre;

    private String dateConstruction;

    private String telephone;

    @ManyToOne(cascade = CascadeType.DETACH)
    @JoinColumn(name = "province_id")
    private Province province;

    @ManyToOne(cascade = CascadeType.DETACH)
    @JoinColumn(name = "commune_id")
    private Commune commune;

    private String adresse;

    @ManyToOne(cascade = CascadeType.DETACH)
    @JoinColumn(name = "responsable_id")
    private Personnel responsable;

    @ManyToOne(cascade = CascadeType.DETACH)
    @JoinColumn(name = "milieuImplantation_id")
    private MilieuImplantation milieuImplantation;

    @ManyToOne(cascade = CascadeType.DETACH)
    @JoinColumn(name = "propriete_du_centre_id")
    private ProprieteDuCentre proprieteDuCentre;

    private double superficie;

    private String utilisation;

    private String etat;

    private String electricite;

    private String telephoneFixe;

    private String internet;

    private int nbrPC;

    private int nbrImprimante;

    private int nbrPersonneConnaissanceInfo;

    private int nbrPersonneOperationelApresFormation;

    private double coutEstimationAmenagement;

    private double coutEstimationEquipement;

    private String observation;
}
