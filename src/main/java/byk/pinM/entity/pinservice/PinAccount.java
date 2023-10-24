package byk.pinM.entity.pinservice;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "PIN_ACCOUNT")
public class PinAccount {
    @Id
    @Column(unique = true)
    private String user_id;

    private String accountnum;
    private String bank_nm;
    private String bank_userNm;

    public String getUser_id() { return user_id; }
    public void setUser_id(String user_id) { this.user_id = user_id; }

    public String getAccountnum() { return accountnum; }
    public void setAccountnum(String accountnum) { this.accountnum = accountnum; }

    public String getBank_nm() { return bank_nm; }
    public void setBank_nm(String bank_nm) { this.bank_nm = bank_nm; }

    public String getBank_userNm() { return bank_userNm; }
    public void setBank_userNm(String bank_userNm) { this.bank_userNm = bank_userNm; }

    //Builder Pattern
    public static class PinAccountBuilder {
        private PinAccount pinAccount = new PinAccount();
        public PinAccountBuilder user_id(String user_id) {
            pinAccount.setUser_id(user_id);
            return this;
        }
        public PinAccountBuilder accountNum(String accountnum) {
            pinAccount.setAccountnum(accountnum);
            return this;
        }
        public PinAccountBuilder bank_nm(String bank_nm) {
            pinAccount.setBank_nm((bank_nm));
            return this;
        }
        public PinAccountBuilder bank_userNm(String bank_userNm) {
            pinAccount.setBank_nm(bank_userNm);
            return this;
        }

        public PinAccount build() { return pinAccount; }
    }

    public static PinAccountBuilder builder() {
        return new PinAccountBuilder();
    }
}
