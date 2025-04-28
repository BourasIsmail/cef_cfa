package ma.entraide.formationcentres.Entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Beneficiaire {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String nom;

    private String prenom;

    private String adresse;

    private String telephone;

    private String dateNaissance;

    private String sexe;

    private String cin;

    private String nationalite;

    private boolean situationHandicap;

    private String numCarteHandicap;

    @ManyToOne(cascade = CascadeType.DETACH)
    @JoinColumn(name = "commune_id")
    private Commune commune;

    @ManyToOne(cascade = CascadeType.DETACH)
    @JoinColumn(name = "province_id")
    private Province province;
    
    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true) 
    private List<Suivie> suivies = new ArrayList<>();

	public Beneficiaire(String nom, String prenom, String adresse, String telephone, String dateNaissance, String sexe,
			String cin,String nationalite,boolean situationHandicap,String numCarteHandicap, Commune commune, Province province) {
		super();
		this.nom = nom;
		this.prenom = prenom;
		this.adresse = adresse;
		this.telephone = telephone;
		this.dateNaissance = dateNaissance;
		this.sexe = sexe;
		this.cin = cin;
        this.nationalite = nationalite;
        this.situationHandicap = situationHandicap;
        this.numCarteHandicap = numCarteHandicap;
		this.commune = commune;
		this.province = province;
	}



}
