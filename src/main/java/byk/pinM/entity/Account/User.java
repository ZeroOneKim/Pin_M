package byk.pinM.entity.Account;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class User {

    @Id
    private Long user_no;

    @Column(unique = true)
    private String user_id;
    private String nickname;
    private String password;
    private String before_pwd;

    private String email;
    private boolean email_chk;
    private String email_token;

    private String phone;
    private String address;
    private String address2;

    private String rgst_dt; //가입일
    private String last_login_dt;
    private int login_err_cnt;
    private boolean use_lock;


    public Long getUser_no() {
        return user_no;
    }
    public void setUser_no(Long user_no) {
        this.user_no = user_no;
    }

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

    public boolean isEmail_chk() {
        return email_chk;
    }
    public void setEmail_chk(boolean email_chk) {
        this.email_chk = email_chk;
    }

    public String getEmail_token() {
        return email_token;
    }
    public void setEmail_token(String email_token) {
        this.email_token = email_token;
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

    public String getRgst_dt() {
        return rgst_dt;
    }
    public void setRgst_dt(String rgst_dt) {
        this.rgst_dt = rgst_dt;
    }

    public String getLast_login_dt() {
        return last_login_dt;
    }
    public void setLast_login_dt(String last_login_dt) {
        this.last_login_dt = last_login_dt;
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
}