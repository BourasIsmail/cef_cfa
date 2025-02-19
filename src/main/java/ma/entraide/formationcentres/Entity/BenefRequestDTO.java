package ma.entraide.formationcentres.Entity;

import lombok.Data;

@Data
public class BenefRequestDTO {
	private String nom;
    private String prenom;
    private String adresse;
    private String telephone;
    private String dateNaissance;
    private String sexe;
    private String cin;
    private Long communeId;
    private Long provinceId;

    // Champs pour Suivie
    private Long filiereId;
    private Long activiteId;
    private Long centreId;
    private String etatDeFormation;
    private String dateEffet;
    private String observation;
}
