package ma.ac.emi.g.info.EmiApplication.userTypes;

import ma.ac.emi.g.info.EmiApplication.Note;
import ma.ac.emi.g.info.EmiApplication.authentication.Authentication;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.OneToMany;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;


@Entity
/**
 * if we use SINGLE_TABLE
 @DiscriminatorValue(value = "Student")
  * */
public class Student extends Personne  {
    private int niveau;
    private boolean anneeReserve;

    @OneToMany(mappedBy = "student")
    @JoinTable(name= "notes_student",
            joinColumns =
                    {@JoinColumn(name = "note_id")},
            inverseJoinColumns =
                    {@JoinColumn(name = "student_id")}
    )
    private List<Note> notes = new ArrayList<>();

    public Student(long id, String nom, String prenom, LocalDate date_naissance, int niveau, boolean anneeReserve) {
        super(id,nom, prenom, date_naissance);
        this.niveau = niveau;
        this.anneeReserve = anneeReserve;
    }

    public Student(Long matricule, String nom, String prenom, LocalDate date_naissance, Integer age, Authentication compte, int niveau, boolean anneeReserve, List<Note> notes) {
        super(matricule, nom, prenom, date_naissance, age, compte);
        this.niveau = niveau;
        this.anneeReserve = anneeReserve;
        this.notes = notes;
    }

    public List<Note> getNotes() {
        return notes;
    }

    public void setNotes(List<Note> notes) {
        this.notes = notes;
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
