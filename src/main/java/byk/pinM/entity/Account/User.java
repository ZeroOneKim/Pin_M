package byk.pinM.entity.Account;

import org.hibernate.validator.constraints.Length;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;

@Entity
@Table(name = "sy_user_mt")
public class User {
    @Id
    @Column(unique = true)
    @Length(min = 3, max = 16)
    private String user_id;

    @NotBlank
    private String nickname;

    @Length(min = 4)
    private String password;
    private String before_pwd;

    @Column(unique = true)
    @Email
    private String email;
    //EMAIL 체크 소스에서.

    private String phone;
    private String address;
    private String address2;

    //가입일 자동입력
    private int login_err_cnt;
    private boolean use_lock;
    private boolean text_agree;
    private int role_id;


    public String getUser_id() {
        return user_id;
    }
    public void setUser_id(String user_id) {
        this.user_id = user_id;
    }

    public String getNickname() {
        return nickname;
    }
    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    public String getPassword() {
        return password;
    }
    public void setPassword(String password) {
        this.password = password;
    }

    public String getBefore_pwd() {
        return before_pwd;
    }
    public void setBefore_pwd(String before_pwd) {
        this.before_pwd = before_pwd;
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

    public int getLogin_err_cnt() {
        return login_err_cnt;
    }
    public void setLogin_err_cnt(int login_err_cnt) {
        this.login_err_cnt = login_err_cnt;
    }

    public boolean isUse_lock() {
        return use_lock;
    }
    public void setUse_lock(boolean use_lock) {
        this.use_lock = use_lock;
    }

    public boolean getText_agree() { return text_agree; }
    public void setText_agree(boolean text_agree) { this.text_agree = text_agree; }

    public int getRole_id() { return role_id; }
    public void setRole_id(int role_id) { this.role_id = role_id; }


    public static UserBuilder builder() {
        return new UserBuilder();
    }

    public static class UserBuilder {
        private User user = new User();
        public UserBuilder user_id(String user_id) {
            user.setUser_id(user_id);
            return this;
        }
        public UserBuilder nickname(String nickname) {
            user.setNickname(nickname);
            return this;
        }
        public UserBuilder password(String password) {
            user.setPassword(password);
            return this;
        }
        public UserBuilder email(String email) {
            user.setEmail(email);
            return this;
        }
        public UserBuilder phone(String phone) {
            user.setPhone(phone);
            return this;
        }
        public UserBuilder address(String address) {
            user.setAddress(address);
            return this;
        }
        public UserBuilder address2(String address2) {
            user.setAddress2(address2);
            return this;
        }
        public UserBuilder login_err_cnt(int errCnt) {
            user.setLogin_err_cnt(errCnt);
            return this;
        }
        public UserBuilder use_lock(boolean use_lock) {
            user.setUse_lock(use_lock);
            return this;
        }
        public UserBuilder text_agree(boolean text_agree) {
            user.setText_agree(text_agree);
            return this;
        }
        public UserBuilder role_id(int role_id) {
            user.setRole_id(role_id);
            return this;
        }


        public User build() {
            return user;
        }
    }
}