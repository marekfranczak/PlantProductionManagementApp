package garden.database.entity;

import jakarta.persistence.*;

@Entity
@Table(name="shops")
public class Shops {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "shop_id")
    private int id;

    @Column(name = "name")
    private String name;

    @Column(name = "address")
    private String address;

    @Column(name = "removed")
    private int removed;

    public Shops(){}

    public int getId() {
        return id;
    }

    public void setId(int shopId) {
        this.id = shopId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public int getRemoved() {
        return removed;
    }

    public void setRemoved(int removed) {
        this.removed = removed;
    }

    @Override
    public String toString() {
        return "Shops{" +
                "Id=" + id +
                ", name='" + name + '\'' +
                ", address='" + address + '\'' +
                ", removed=" + removed +
                '}';
    }
}
