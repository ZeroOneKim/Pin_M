package byk.pinM.entity.Account.get;

import org.hibernate.validator.constraints.Length;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class PasswordUpdate {
    @Id
    @Length(min = 8)
    private String newPassword;

    @Length(min = 8)
    private String password;



    public String getPassword() { return password; }
    public void setPassword(String password) { this.password = password; }

    public String getNewPassword() { return newPassword; }
    public void setNewPassword(String newPassword) { this.newPassword = newPassword; }


}
