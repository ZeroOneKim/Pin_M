package byk.pinM.entity.Account;

public class SignUpResponse {
    //JavaScript 경우 개발자도구에서 변환이 가능하므로 후에 검증 소스 추가.
    // 아직 필요없는 소스
    private String user_id;
    private String nickname;
    private String password;
    private String email;
    private String email_chk;
    private String phone;
    private String address;
    private String address2;

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

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getEmail_chk() {
        return email_chk;
    }

    public void setEmail_chk(String email_chk) {
        this.email_chk = email_chk;
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
