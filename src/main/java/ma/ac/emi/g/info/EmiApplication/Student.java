package ma.ac.emi.g.info.EmiApplication;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import java.time.LocalDate;


@Entity
@DiscriminatorValue(value = "Student")
public class Student extends Personne  {
    private int niveau;
    private boolean anneeReserve;

    public Student(long id, String nom, String prenom, LocalDate date_naissance, int niveau, boolean anneeReserve) {
        super(id,nom, prenom, date_naissance);
        this.niveau = niveau;
        this.anneeReserve = anneeReserve;
    }

    public Student() {
    }

    public int getNiveau() {
        return niveau;
    }

    public void setNiveau(int niveau) {
        this.niveau = niveau;
    }

    public boolean isAnneeReserve() {
        return anneeReserve;
    }

    public void setAnneeReserve(boolean anneeReserve) {
        this.anneeReserve = anneeReserve;
    }
}
