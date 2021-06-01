package ma.ac.emi.g.info.EmiApplication.TwoIds;

import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class Personne2 {

    @Id
    @Embedded
    private User user;
    private int age;

    public Personne2() {
    }

    public Personne2(User user, int age) {
        this.user = user;
        this.age = age;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

}
