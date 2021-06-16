package ma.ac.emi.g.info.EmiApplication.userTypes;

import javax.persistence.Entity;
import java.time.LocalDate;


@Entity
/**
 * if we use SINGLE_TABLE
 @DiscriminatorValue(value = "Professeur")
  * */
public class Professeur extends Personne {
    private int numSomme;
    private int indice = 500;

    public Professeur(int numSomme, int indice) {
        this.numSomme = numSomme;
        this.indice = indice;
    }

    public Professeur(long id, String nom, String prenom, LocalDate date_naissance, int numSomme, int indice) {
        super(id, nom, prenom, date_naissance);
        this.numSomme = numSomme;
        this.indice = indice;
    }

    public Professeur() {
    }

    public int getNumSomme() {
        return numSomme;
    }

    public void setNumSomme(int numSomme) {
        this.numSomme = numSomme;
    }

    public int getIndice() {
        return indice;
    }

    public void setIndice(int indice) {
        this.indice = indice;
    }
}
