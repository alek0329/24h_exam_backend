package entities;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name="Owner")
public class Owner {

    @Id
    @Column(name = "ownerID")
    @NotNull
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    private int ownerId;
    @Column (name ="name")
    private String name;
    @Column (name = "address")
    private String address;
    @Column (name = "phone")
    private String phone;

    @ManyToMany(mappedBy = "owners",cascade = CascadeType.PERSIST)
    private List<Boat> boats;

    public Owner(String name, String address, String phone) {
        this.ownerId = ownerId;
        this.name = name;
        this.address = address;
        this.phone = phone;
        this.boats = new ArrayList<>();
    }

    public Owner() {
    }

    public void addBoat(Boat boat) {
        if (boat != null) {
            this.boats.add(boat);
            boat.getOwners().add(this);
        }
    }


    public int getOwnerId() {
        return ownerId;
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

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public List<Boat> getBoats() {
        return boats;
    }

}