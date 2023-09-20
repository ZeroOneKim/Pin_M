package byk.pinM.entity.Account.get;

import byk.pinM.entity.Account.User;
import org.hibernate.validator.constraints.Length;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.validation.constraints.NotBlank;

@Entity
public class Profile {
    @Id
    @Length(min = 2, max = 12)
    private String nickname;

    private String email;
    private String phone;
    private String address;
    private String address2;

    public Profile() {
    }

    public Profile(User user) {
        this.nickname = user.getNickname();
        this.email    = user.getEmail();
        this.phone    = user.getPhone();
        this.address  = user.getAddress();
        this.address2 = user.getAddress2();
    }

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getAddress2() {
        return address2;
    }

    public void setAddress2(String address2) {
        this.address2 = address2;
    }
}
