package ma.ac.emi.g.info.EmiApplication;

import javax.persistence.Id;
import javax.persistence.OneToMany;
import java.util.List;

public class Matiere {
    @Id
    private Long id;

    @OneToMany(mappedBy = "matiere")
    private List<Note> notes;

    public Matiere(Long id, List<Note> notes) {
        this.id = id;
        this.notes = notes;
    }

    public Matiere() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public List<Note> getNotes() {
        return notes;
    }

    public void setNotes(List<Note> notes) {
        this.notes = notes;
    }
}
