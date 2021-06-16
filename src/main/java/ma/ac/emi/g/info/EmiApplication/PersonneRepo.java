package ma.ac.emi.g.info.EmiApplication;

import ma.ac.emi.g.info.EmiApplication.userTypes.Personne;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface PersonneRepo  extends JpaRepository<Personne,Long> {
    public List<Personne> findByNom(String nom);

    /**
     * in this query, Personne is the class and p is the object ( instance of the class )
     */

    @Query("SELECT p.nom from Personne p")
    public String nomPersonne(String id);
}
