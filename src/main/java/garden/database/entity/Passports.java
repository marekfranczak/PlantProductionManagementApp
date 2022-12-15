package garden.database.entity;

import jakarta.persistence.*;

@Entity
@Table(name="passports")
public class Passports {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="passports_id")
    private int id;
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "shop_id")
    private Shops shop;
    @Column(name = "date")
    private String data;
    @Column(name = "removed")
    private int removed;

    //private String shopName;

    public Passports(){}

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Shops getShop() {
        return shop;
    }

    public void setShop(Shops shop) {
        this.shop = shop;
    }

    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
    }

    public int getRemoved() {
        return removed;
    }

    public void setRemoved(int removed) {
        this.removed = removed;
    }

//    public String getShopName() {
//        return shopName;
//    }
//
//    public void setShopName(String shopName) {
//        this.shopName = shopName;
//    }

    @Override
    public String toString() {
        return "Passports{" +
                "passportsId=" + id +
                ", shopId=" + shop +
                ", data='" + data + '\'' +
                ", removed=" + removed +
                '}';
    }
}
