package garden.database.entity;


import jakarta.persistence.*;

@Entity
@Table(name="flowers")
public class Flowers {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="flower_id")
    private int id;

    @Column(name="name_pl")
    private String namePL;

    @Column(name="name_la")
    private String nameLA;

    @Column(name="removed")
    private int removed;

    public Flowers(){}

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNamePL() {
        return namePL;
    }

    public void setNamePL(String namePL) {
        this.namePL = namePL;
    }

    public String getNameLA() {
        return nameLA;
    }

    public void setNameLA(String nameLA) {
        this.nameLA = nameLA;
    }

    public int getRemoved() {
        return removed;
    }

    public void setRemoved(int removed) {
        this.removed = removed;
    }

    @Override
    public String toString() {
        return "Flowers{" +
                "id=" + id +
                ", namePL='" + namePL + '\'' +
                ", nameLA='" + nameLA + '\'' +
                ", removed=" + removed +
                '}';
    }
}
