package ma.ac.emi.g.info.EmiApplication;

import javax.persistence.*;
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;


/**
 * Inheritance to explicit that Student is part of Personne
 * strategies are :
 * JOINED
 * Single-TABLE
 * TABLE_PER_CLASS
 * */

@Entity
@Inheritance(strategy = InheritanceType.TABLE_PER_CLASS)
@DiscriminatorColumn(name = "type", discriminatorType = DiscriminatorType.STRING)
@DiscriminatorValue(value = "Personne")

public class Personne {
    /**
     * Every attribute is seralized using the annotation @Entity if not serialized, an implementation should be added
     * else, an annotation @Transient The seralization problem is solved but a problem may occur in the RESTFULL
     * To generate the JSON file add annotation @JsonIgnore
     *
     * generation type :
     * IDENTITY = Auto Increment
     * SEQUENCE =   generate a random sequence values and check if it never existed
     *              saved in a table using the annotation @SequenceGenerator to define how to generate
     * TABLE = generate ID from a specific table using @TableGenerator
     */

    @Id
    //@GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long matricule;
    private String nom;
    private String prenom;
    private LocalDate date_naissance;
    @Transient
    private Integer age;

    /*@Transient
    @JsonIgnore
    private Personne2 personne2;*/

    public Personne(long id, String nom, String prenom, LocalDate date_naissance) {
        this.matricule = id;
        this.nom = nom;
        this.prenom = prenom;
        this.date_naissance = date_naissance;
        this.age = (int) ChronoUnit.YEARS.between(LocalDate.now(), date_naissance);
    }

    public Personne() {
    }

    public Long getMatricule() {
        return matricule;
    }

    public void setMatricule(Long matricule) {
        this.matricule = matricule;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getPrenom() {
        return prenom;
    }

    public void setPrenom(String prenom) {
        this.prenom = prenom;
    }

    public LocalDate getDate_naissance() {
        return date_naissance;
    }

    public void setDate_naissance(LocalDate date_naissance) {
        this.date_naissance = date_naissance;
    }
}
