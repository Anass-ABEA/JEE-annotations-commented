package ma.ac.emi.g.info.EmiApplication.TwoIds;

import javax.persistence.Embeddable;
import java.io.Serializable;
import java.util.Objects;

@Embeddable
public class User  implements Serializable {
    private static final long serialVersionUID = 3293663207501592436L;
    private String fName;
    private String lName;


    public User() {
    }

    public String getfName() {
        return fName;
    }

    public void setfName(String fName) {
        this.fName = fName;
    }

    public String getlName() {
        return lName;
    }

    public void setlName(String lName) {
        this.lName = lName;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        User user = (User) o;
        return Objects.equals(fName, user.fName) &&
                Objects.equals(lName, user.lName);
    }

    @Override
    public int hashCode() {
        return Objects.hash(fName, lName);
    }
}

