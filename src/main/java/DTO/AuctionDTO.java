package DTO;

import entities.Auction;
import entities.Boat;

import java.util.ArrayList;
import java.util.List;

public class AuctionDTO {

    private int auctionId;
    private String name;
    private String date;
    private String time;
    private String location;
    private List<BoatDTO> boats;

    public AuctionDTO() {
    }

    public AuctionDTO(Auction auction) {
        this.auctionId = auction.getAuctionId();
        this.name = auction.getName();
        this.date = auction.getDate();
        this.time = auction.getTime();
        this.location = auction.getLocation();
      

    }

  public static List <AuctionDTO> getDTO(List<Auction> auctions) {
        if (auctions != null) {
            List <AuctionDTO> auctionsDTO = new ArrayList<>();
            auctions.forEach(a -> auctionsDTO.add(new AuctionDTO(a)));
            return auctionsDTO;
        }else {
            return null;
        }
  }

    public int getAuctionId() {
        return auctionId;
    }

    public void setAuctionId(int auctionId) {
        this.auctionId = auctionId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getTime() {
        return time;
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
}
