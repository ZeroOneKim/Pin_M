package byk.pinM.entity.Account;


import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "sy_user_log_ht")
public class UserLog {
    @Id
    @Column(unique = true)
    private Long log_id;

    private String user_id;
    private String access_dt;
    private String ip_addr;
    private String page_nm;

    public Long getLog_id() { return log_id; }
    public void setLog_id(Long log_id) { this.log_id = log_id; }

    public String getUser_id() { return user_id; }
    public void setUser_id(String user_id) { this.user_id = user_id; }

    public String getAccess_dt() { return access_dt; }
    public void setAccess_dt(String access_dt) { this.access_dt = access_dt; }

    public String getIp_addr() { return ip_addr; }
    public void setIp_addr(String ip_addr) { this.ip_addr = ip_addr; }

    public String getPage_nm() { return page_nm; }
    public void setPage_nm(String page_nm) { this.page_nm = page_nm; }


    public UserLogBuilder builder() {
        return new UserLogBuilder();
    }

    public class UserLogBuilder {
        UserLog userLog = new UserLog();
        public UserLogBuilder logId(Long log_id) {
            userLog.setLog_id(log_id);
            return this;
        }
        public UserLogBuilder userId (String user_id) {
            userLog.setUser_id(user_id);
            return this;
        }
        public UserLogBuilder AccessDate(String access_dt) {
            userLog.setAccess_dt(access_dt);
            return this;
        }
        public UserLogBuilder ipAddress(String ip_addr) {
            userLog.setIp_addr(ip_addr);
            return this;
        }
        public UserLogBuilder pageNm(String page_nm) {
            userLog.setPage_nm(page_nm);
            return this;
        }

        public UserLog build() {
            return userLog;
        }
    }
}
