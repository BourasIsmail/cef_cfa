package ma.entraide.formationcentres.Entity;

import java.util.ArrayList;
import java.util.List;


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

    //qui paye
    private String electricite;

    private String telephoneFixe;

    private String internet;

    //Etat Foncier
    private String possession;
    
    private double montantAllocation;

    private int nbrPC;

    private int nbrImprimante;

    private int nbrPersonneConnaissanceInfo;

    private int nbrPersonneOperationelApresFormation;

    private double coutEstimationEquipement;

    private float latitude;

    private float longitude;

    private String naturePropriete;
    
    
    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true) 
    private List<CentreFacture> factures = new ArrayList<>();

	public Centre(String nomFr, String nomAr, TypeCentre typeCentre, String dateConstruction,
			Province province, Commune commune, String adresse, Personnel responsable,
			MilieuImplantation milieuImplantation,String naturePropriete, ProprieteDuCentre proprieteDuCentre, double superficie,
			 String electricite, String telephoneFixe, String internet, int nbrPC,
			int nbrImprimante, int nbrPersonneConnaissanceInfo, int nbrPersonneOperationelApresFormation,
			 double coutEstimationEquipement, float latitude,
			float longitude, String possession ) {
		this.nomFr = nomFr;
		this.nomAr = nomAr;
		this.typeCentre = typeCentre;
		this.dateConstruction = dateConstruction;
		this.province = province;
		this.commune = commune;
		this.adresse = adresse;
		this.responsable = responsable;
		this.milieuImplantation = milieuImplantation;
		this.proprieteDuCentre = proprieteDuCentre;
		this.superficie = superficie;
		this.electricite = electricite;
		this.telephoneFixe = telephoneFixe;
		this.internet = internet;
		this.possession = possession;
		this.nbrPC = nbrPC;
		this.nbrImprimante = nbrImprimante;
		this.nbrPersonneConnaissanceInfo = nbrPersonneConnaissanceInfo;
		this.nbrPersonneOperationelApresFormation = nbrPersonneOperationelApresFormation;
		this.coutEstimationEquipement = coutEstimationEquipement;
		this.latitude = latitude;
		this.longitude = longitude;
        this.naturePropriete = naturePropriete;
	}

    
}
