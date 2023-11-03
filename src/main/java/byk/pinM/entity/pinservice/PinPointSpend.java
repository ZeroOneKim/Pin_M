package byk.pinM.entity.pinservice;

import javax.persistence.*;

@Entity
@Table(name = "pin_pt_spend_mt")
public class PinPointSpend {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long spend_id;

    private String user_id;
    private int spend_point;
    private String spend_dt;

    public Long getSpend_id() { return spend_id; }
    public void setSpend_id(Long spend_id) { this.spend_id = spend_id; }

    public String getUser_id() { return user_id; }
    public void setUser_id(String user_id) { this.user_id = user_id; }

    public int getSpend_point() { return spend_point; }
    public void setSpend_point(int spend_point) { this.spend_point = spend_point; }

    public String getSpend_dt() { return spend_dt; }
    public void setSpend_dt(String spend_dt) { this.spend_dt = spend_dt; }
}
