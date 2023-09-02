package byk.pinM.entity.Account;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class User {

    @Id
    @Column(unique = true)
    private String user_id;
    private String nickname;
    private String password;
    private String before_pwd;

    @Column(unique = true)
    private String email;
    //EMAIL 체크 소스에서.

    private String phone;
    private String address;
    private String address2;

    //가입일 자동입력
    private int login_err_cnt;
    private boolean use_lock;
    private Boolean text_agree;

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
    public void setText_agree() { this.text_agree = text_agree; }
}