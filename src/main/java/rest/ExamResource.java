package rest;

import DTO.AuctionDTO;
import DTO.BoatDTO;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import entities.Auction;
import entities.Boat;
import facades.AuctionFacade;
import facades.BoatFacade;
import utils.EMF_Creator;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.TypedQuery;
import javax.ws.rs.*;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.SecurityContext;
import javax.ws.rs.core.UriInfo;
import java.util.List;

@Path("boathub")
public class ExamResource {

    private static final EntityManagerFactory EMF = EMF_Creator.createEntityManagerFactory();
    private static final AuctionFacade AUCTION_FACADE = AuctionFacade.getAuctionFacade(EMF);
    private static final BoatFacade BOAT_FACADE = BoatFacade.getBoatFacade(EMF);
    private Gson GSON = new GsonBuilder().setPrettyPrinting().create();

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Path("auctions")
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
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Path("auction/{auctionId}")
        public String getAuctionBoats (@PathParam("auctionId") int auctionId) throws NotFoundException {
        List <BoatDTO> allBoatsInAuction = null;
        if (auctionId != 0){
            try {
                allBoatsInAuction = BOAT_FACADE.getAllBoatsInAuction(auctionId);
            } catch (Exception e){
                throw new NotFoundException("No boats were found");
            }
        }else {
            throw new NotFoundException("Missing Auction ID");
        }if (allBoatsInAuction != null) {
            System.out.println(allBoatsInAuction.size());
            return GSON.toJson(allBoatsInAuction);
        } else{
            throw new NotFoundException("no Boats in this Auction");
        }
    }

}
