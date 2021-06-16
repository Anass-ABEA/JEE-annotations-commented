package ma.ac.emi.g.info.EmiApplication;

import ma.ac.emi.g.info.EmiApplication.userTypes.Student;
import org.springframework.data.annotation.Reference;

import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

public class Note {
    @Id
    private Long id;
    private float note;
    private String apreciation;


    private Matiere matiere;

    @ManyToOne(targetEntity = Student.class)
    @JoinColumn(name="etudiant",referencedColumnName = "notes")
    private Student student;

    public Note(Long id, float note, String apreciation, Student student) {
        this.id = id;
        this.note = note;
        this.apreciation = apreciation;
        this.student = student;
    }

    public Note() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public float getNote() {
        return note;
    }

    public void setNote(float note) {
        this.note = note;
    }

    public String getApreciation() {
        return apreciation;
    }

    public void setApreciation(String apreciation) {
        this.apreciation = apreciation;
    }

    public Student getStudent() {
        return student;
    }

    public void setStudent(Student student) {
        this.student = student;
    }
}

