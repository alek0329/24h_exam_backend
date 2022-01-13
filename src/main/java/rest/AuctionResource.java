package rest;

import DTO.AuctionDTO;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import entities.Auction;
import entities.Boat;
import facades.AuctionFacade;
import utils.EMF_Creator;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.TypedQuery;
import javax.ws.rs.GET;
import javax.ws.rs.NotFoundException;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.SecurityContext;
import javax.ws.rs.core.UriInfo;
import java.util.List;

@Path("auctions")
public class AuctionResource {

    private static final EntityManagerFactory EMF = EMF_Creator.createEntityManagerFactory();
    private static final AuctionFacade AUCTION_FACADE = AuctionFacade.getAuctionFacade(EMF);
    private Gson GSON = new GsonBuilder().setPrettyPrinting().create();

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public String allAuctions() {
        List<AuctionDTO> auctionDTO = null;
        try{
            auctionDTO = AUCTION_FACADE.getAllAuctions();
            System.out.println(auctionDTO.size());
        }catch (Exception e) {
            throw new NotFoundException("Error getting all auctions");
        }
        if (auctionDTO != null && !auctionDTO.isEmpty()) {
            return GSON.toJson(auctionDTO);
        }else {
            throw new NotFoundException("Error getting all auctions");
        }
    }
}
