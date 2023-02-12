package garden.database.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotEmpty;

import java.util.HashSet;
import java.util.Set;

/**
 * Entity of Passports table from database.
 * @author Marek Frańczak
 * @since 2.0.0
 */
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
    @NotEmpty(message = "Passport data cannot be empty!")
    private String data;
    @Column(name = "removed")
    private int removed;

    @ManyToMany(fetch = FetchType.LAZY, cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    @JoinTable(name="flowers_passport_link",
            joinColumns = @JoinColumn(name = "passport_id"),
            inverseJoinColumns = @JoinColumn(name="flower_id"))
    private Set<Flowers> flowers = new HashSet<>();


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

    public Set<Flowers> getFlowers() {
        if (flowers == null)
            flowers = new HashSet<>();
        return flowers;
    }

    public void setFlowers(Set<Flowers> flowers) {
        this.flowers = flowers;
    }

    public void addFlower(Flowers flower){

        this.flowers.add(flower);
        flower.getPassports().add(this);

    }

    public void deleteFlower(Flowers flower){
        if(flowers != null){
            this.flowers.remove(flower);
            flower.getPassports().remove(this);
        }

    }

    @Override
    public String toString() {
        return "Passports{" +
                "id=" + id +
                ", shop=" + shop +
                ", data='" + data + '\'' +
                ", removed=" + removed +
                ", flowers=" + flowers +
                '}';
    }
}
