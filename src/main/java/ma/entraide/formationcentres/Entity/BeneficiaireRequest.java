package ma.entraide.formationcentres.Entity;

import java.util.List;

import lombok.Data;

@Data
public class BeneficiaireRequest {
    private String nom;
    private String prenom;
    private String adresse;
    private String telephone;
    private String dateNaissance;
    private String sexe;
    private String cin;
    private Commune commune;
    private Province province;
    private String nationalite;
    private boolean situationHandicap;
    private String numCarteHandicap;
    private List<Suivie> suivies;
}