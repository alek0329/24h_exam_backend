package DTO;

import entities.Auction;
import entities.Boat;

import java.util.ArrayList;
import java.util.List;

public class BoatDTO {

    private int boatID;
    private String brand;
    private String name;
    private String image;
    private int year;
    private List <OwnerDTO> owners;
    private AuctionDTO auction;


    public BoatDTO() {
    }

    public BoatDTO(Boat boat) {
        this.boatID = boat.getBoatId();
        this.name = boat.getName();
        this.year = boat.getYear();
        this.brand = boat.getBrand();
        this.owners = OwnerDTO.getDTO(boat.getOwners());
        this.image = boat.getImage();
        this.auction = new AuctionDTO(boat.getAuction());
    }

    public static List<BoatDTO> getDTO(List<Boat> boats){
        if (boats != null) {
            List <BoatDTO> boatsDTO = new ArrayList<>();
            boats.forEach(b -> boatsDTO.add(new BoatDTO(b)));
            return boatsDTO;
        }else {
            return null;
        }
    }


    public int getBoatID() {
        return boatID;
    }

    public void setBoatID(int boatID) {
        this.boatID = boatID;
    }

    public String getBrand() {
        return brand;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }

    public List<OwnerDTO> getOwners() {
        return owners;
    }

    public void setOwners(List<OwnerDTO> owners) {
        this.owners = owners;
    }

    public AuctionDTO getAuction() {
        return auction;
    }

    public void setAuction(AuctionDTO auction) {
        this.auction = auction;
    }
}
