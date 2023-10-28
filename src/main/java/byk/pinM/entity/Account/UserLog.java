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
}
