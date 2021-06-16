package ma.ac.emi.g.info.EmiApplication.userTypes;

import ma.ac.emi.g.info.EmiApplication.authentication.Authentication;
import org.springframework.lang.NonNull;

import javax.persistence.*;
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;


/**
 * Inheritance to explicit that Student is part of Personne
 * strategies are : `strategy = InheritanceType.`
 * JOINED => Creates a table of the main class and 2 classes of the inherited classes for a join call
 * SINGLE_TABLE => creates one single table with a column -Discriminator- with the data type
 * TABLE_PER_CLASS => Creates a table for the child with the parameters of the parent
 *
 * if we use SINGLE_TABLE we can add a descriminator
 * @DiscriminatorColumn(name = "type", discriminatorType = DiscriminatorType.STRING)
 * @DiscriminatorValue(value = "Personne")
 * Discriminator may be NULLABLE!
 *
 * Adding a Class as a param of the class wouldn't work if it wasn't specified with
 * a link type
 *
 */

@Entity
@Inheritance(strategy = InheritanceType.JOINED)
public class Personne {
    /**
     * Every attribute is seralized using the annotation @Entity if not serialized, an implementation should be added
     * else, an annotation @Transient The seralization problem is solved but a problem may occur in the RESTFULL
     * To generate the JSON file add annotation @JsonIgnore
     * <p>
     * generation type :
     * IDENTITY = Auto Increment
     * SEQUENCE =   generate a random sequence values and check if it never existed
     * saved in a table using the annotation @SequenceGenerator to define how to generate
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

    @OneToOne(mappedBy = "personne")
    @PrimaryKeyJoinColumn
    private Authentication compte;

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

    public Personne(Long matricule, String nom, String prenom, LocalDate date_naissance, Integer age, Authentication compte) {
        this.matricule = matricule;
        this.nom = nom;
        this.prenom = prenom;
        this.date_naissance = date_naissance;
        this.age = age;
        this.compte = compte;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public Authentication getCompte() {
        return compte;
    }

    public void setCompte(Authentication compte) {
        this.compte = compte;
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
