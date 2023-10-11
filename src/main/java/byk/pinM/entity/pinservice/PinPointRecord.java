package byk.pinM.entity.pinservice;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import java.io.Serializable;
import java.util.Date;

@Entity
public class PinPointRecord implements Serializable {
    @Id
    private Date record_dt;

    @Id
    @Column(unique = true)
    private String user_id;

    private int mission_id;

    private int price;

    public Date getRecord_dt() { return record_dt; }
    public void setRecord_dt(Date record_dt) { this.record_dt = record_dt; }

    public String getUser_id() { return user_id; }
    public void setUser_id(String user_id) { this.user_id = user_id; }

    public int getMission_id() { return mission_id; }
    public void setMission_id(int mission_id) { this.mission_id = mission_id; }

    public int getPrice() { return price; }
    public void setPrice(int price) { this.price = price; }

    public static class PinPointRecordBuilder {
        PinPointRecord pinPointRecord = new PinPointRecord();
        public PinPointRecordBuilder user_id(String user_id) {
            pinPointRecord.setUser_id(user_id);
            return this;
        }
        public PinPointRecordBuilder mission_id(int mission_id) {
            pinPointRecord.setMission_id(mission_id);
            return this;
        }
        public PinPointRecordBuilder price(int price) {
            pinPointRecord.setPrice(price);
            return this;
        }
        public PinPointRecord build() { return pinPointRecord; }
    }
    public static PinPointRecordBuilder builder() { return new PinPointRecordBuilder(); }

}
