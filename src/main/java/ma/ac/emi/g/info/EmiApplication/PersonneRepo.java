package ma.ac.emi.g.info.EmiApplication;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface PersonneRepo  extends JpaRepository<Personne,Long> {
    public List<Personne> findByNom(String nom);
}
