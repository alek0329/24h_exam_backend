package entities;

import org.eclipse.persistence.jpa.config.Cascade;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name="Auction")
public class Auction {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="AuctionID")
    @NotNull
    private int auctionId;
    @Column (name="Name")
    private String name;
    @Column (name ="Date")
    private String date;
    @Column (name="Time")
    private String time;
    @Column (name="Location")
    private String location;

    @OneToMany (mappedBy="auction", cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    private List<Boat> boats = new ArrayList<Boat>();

    public Auction(String name, String date, String time, String location) {
        this.auctionId = auctionId;
        this.name = name;
        this.date = date;
        this.time = time;
        this.location = location;
        this.boats = new ArrayList<>();
    }

    public Auction() {
    }

    public void addBoat (Boat boat) {
        this.boats.add(boat);
        if (boat != null) {
            boat.setAuction(this);
        }
    }

    public void boatRemover (Boat boat) {boats.remove(boat);}

    public int getAuctionId() {
        return auctionId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getTime() {
        return time;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public List<Boat> getBoats() {
        return boats;
    }

}
