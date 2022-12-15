package garden.database.entity;

import jakarta.persistence.*;

@Entity
@Table(name="flowers_arrangement")
public class FlowersArrangement {

    @Id
    @Column(name="passport_id")
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "passports_id")
    private int passportId;
    @Id
    @Column(name="flower_id")
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "shop_id")
    private int flowerId;

    public int getPassportId() {
        return passportId;
    }

    public void setPassportId(int passportId) {
        this.passportId = passportId;
    }

    public int getFlowerId() {
        return flowerId;
    }

    public void setFlowerId(int flowerId) {
        this.flowerId = flowerId;
    }
}
