package garden.database.entity;


import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;

import java.util.HashSet;
import java.util.Set;

/**
 * Entity of Flowers table from database.
 * @author Marek Frańczak
 * @since 2.0.0
 */
@Entity
@Table(name="flowers")
public class Flowers {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="flower_id")
    private int id;

    @Column(name="name_pl")
    @Size(min = 1, max = 250)
    private String namePL;

    @Column(name="name_la")
    @NotEmpty(message = "Latin name cannot be empty!")
    @Size(min = 1, max = 250)
    private String nameLA;

    @Column(name="removed")
    private int removed;

    @JsonIgnore
    @ManyToMany(mappedBy = "flowers")
    private Set<Passports> passports = new HashSet<>();

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

    public Set<Passports> getPassports() {
        return passports;
    }

    public void setPassports(Set<Passports> passports) {
        this.passports = passports;
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
