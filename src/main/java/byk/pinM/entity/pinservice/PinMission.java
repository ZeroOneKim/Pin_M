package byk.pinM.entity.pinservice;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "PIN_MISSION_MT")
public class PinMission {
    @Id
    private int mission_id;

    private String mission_nm;
    private int price;

    public int getMission_id() { return mission_id; }
    public void setMission_id(int mission_id) { this.mission_id = mission_id; }

    public String getMission_nm() { return mission_nm; }
    public void setMission_nm(String mission_nm) { this.mission_nm = mission_nm; }

    public int getPrice() { return price; }
    public void setPrice(int price) { this.price = price; }


    public static class PinMissionBuilder {
        private PinMission pinMission = new PinMission();
        public PinMissionBuilder missionId(int mission_id) {
            pinMission.setMission_id(mission_id);
            return this;
        }
        public PinMissionBuilder missionNm(String mission_nm) {
            pinMission.setMission_nm(mission_nm);
            return this;
        }
        public PinMissionBuilder price(int price) {
            pinMission.setPrice(price);
            return this;
        }
        public PinMission build() {
            return pinMission;
        }
    }
    public static PinMissionBuilder builder() { return new PinMissionBuilder(); }
}
