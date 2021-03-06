package entities;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name="Boat")
public class Boat {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @NotNull
    @Column (name ="boatID")
    private int boatId;
    @NotBlank
    @Column (name ="brand")
    private String brand;
    @NotBlank
    @Column (name ="name")
    private String name;
    @NotBlank
    @Column (name="Year")
    private int year;
    @NotBlank
    @Column (name="ImageUrl")
    private String image;

    public Boat() {
    }

    @ManyToMany
    @JoinColumn(name="user_name")
    private List <Owner> owners;

    @ManyToOne
    @JoinColumn(name="AuctionID")
    private Auction auction;

    public Boat(String name,String brand, int year, String image) {
        this.boatId = boatId;
        this.name = name;
        this.brand = brand;
        this.year = year;
        this.image = image;
        this.owners = new ArrayList<>();
        this.auction = auction;
    }

    public void removeFromAuction(Boat boat){
        boat.setAuction(null);
    }

    public int getBoatId() {
        return boatId;
    }

    public String getBrand() {
        return brand;
    }

    public String getName() {
        return name;
    }


    public List<Owner> getOwners() {
        return owners;
    }

    public int getYear() {
        return year;
    }

    public String getImage() {
        return image;
    }

    public Auction getAuction() {
        return auction;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }

    public void setName(String name) {
        this.name = name;
    }


    public void setOwner(List<Owner> owners) {
        this.owners = owners;
    }

    public void setAuction(Auction auction) {
        this.auction = auction;
    }
}
