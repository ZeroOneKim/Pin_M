package byk.pinM.entity.pinservice;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "PIN_POINT_MT")
public class PinPoint {
    @Id
    @Column(unique = true)
    String user_id;

    int pin_point;
    int use_point;

    public String getUser_id() { return user_id; }
    public void setUser_id(String user_id) { this.user_id = user_id; }

    public int getPin_point() { return pin_point; }
    public void setPin_point(int pin_point) { this.pin_point = pin_point; }

    public int getUse_point() { return use_point; }
    public void setUse_point(int use_point) { this.use_point = use_point; }

    public static class pinPointBuilder {
        private PinPoint pinPoint = new PinPoint();
        public pinPointBuilder user_id(String user_id) {
            pinPoint.setUser_id(user_id);
            return this;
        }
        public pinPointBuilder pin_point(int pin_point) {
            pinPoint.setPin_point(pin_point);
            return this;
        }
        public pinPointBuilder use_point(int use_point) {
            pinPoint.setUse_point(use_point);
            return this;
        }
        public PinPoint build() {
            return pinPoint;
        }
    }

    public static pinPointBuilder builder() {
        return new pinPointBuilder();
    }
}
