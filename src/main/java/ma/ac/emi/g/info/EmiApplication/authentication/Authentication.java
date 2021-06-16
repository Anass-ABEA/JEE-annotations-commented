package ma.ac.emi.g.info.EmiApplication.authentication;

import ma.ac.emi.g.info.EmiApplication.userTypes.Personne;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;

/**
 * @OneToOne unidirectional or bidirectional
 *  specify Cascade:
 *
 *  specify fetch type ( LAZY[ not instantly ] or EAGER [ instantly fetched ] )
 *
 * */

@Entity
public class Authentication {
    @Id
    private Long id;                    // is references to the Student Matricule parameter
    private String login,password;
    private boolean isActive;

    @OneToOne
    @JoinColumn(name="id",referencedColumnName = "matricule") // référence de l'attribut de la table Personne
    private Personne personne;

    public Authentication(String login, String password, boolean isActive) {
        this.login = login;
        this.password = password;
        this.isActive = isActive;
    }

    public Authentication() {
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public boolean isActive() {
        return isActive;
    }

    public void setActive(boolean active) {
        isActive = active;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Personne getPersonne() {
        return personne;
    }

    public void setPersonne(Personne personne) {
        this.personne = personne;
    }

    @Override
    public String toString() {
        return "authentication{" +
                "login='" + login + '\'' +
                ", password='" + password + '\'' +
                ", isActive=" + isActive +
                '}';
    }
}
