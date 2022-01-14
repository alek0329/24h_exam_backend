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

import javax.annotation.security.RolesAllowed;
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
        try {
            auctionDTO = AUCTION_FACADE.getAllAuctions();
            System.out.println(auctionDTO.size());
        } catch (Exception e) {
            throw new NotFoundException("Error getting all auctions");
        }
        if (auctionDTO != null && !auctionDTO.isEmpty()) {
            return GSON.toJson(auctionDTO);
        } else {
            throw new NotFoundException("Error getting all auctions");
        }
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Path("myboats")
    public String getOwnerBoats(String userName) {
        List<BoatDTO> allMyBoats = null;
        userName = "alek";
        if (userName != null) {
            try {
                allMyBoats = BOAT_FACADE.getAllOwnerBoats(userName);
            } catch (Exception e) {
                throw new NotFoundException("Boats not found");
            }
        } else {
            throw new NotFoundException("missing Username");
        }
        if (allMyBoats != null && !allMyBoats.isEmpty()) {
            return GSON.toJson(allMyBoats);
        } else {
            throw new NotFoundException("Boats not found");
        }
    }


    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Path("auction/{auctionId}")
    public String getAuctionBoats(@PathParam("auctionId") int auctionId) throws NotFoundException {
        List<BoatDTO> allBoatsInAuction = null;
        if (auctionId != 0) {
            try {
                allBoatsInAuction = BOAT_FACADE.getAllBoatsInAuction(auctionId);
            } catch (Exception e) {
                throw new NotFoundException("No boats were found");
            }
        } else {
            throw new NotFoundException("Missing Auction ID");
        }
        if (allBoatsInAuction != null) {
            System.out.println(allBoatsInAuction.size());
            return GSON.toJson(allBoatsInAuction);
        } else {
            throw new NotFoundException("no Boats in this Auction");
        }
    }

    @DELETE
    @Path("removefromauction/{auctionId}/{boatId}")
    @RolesAllowed("admin")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public String removeBoatFromAuction(@PathParam("auctionId") int auctionId, @PathParam("boatId") int boatId) {
        AuctionDTO auctionDTO = null;
        if (boatId != 0 && auctionId != 0) {
            try {
                auctionDTO = AUCTION_FACADE.removeBoat(boatId, auctionId);
            } catch (Exception e) {
                throw new NotFoundException("Boat could not be removed");
            }
        } else {
            throw new NotFoundException("Missing ID");
        }
        if (auctionDTO != null) {
            return GSON.toJson(auctionDTO);
        } else {
            throw new NotFoundException("Boat could not be removed");
        }
    }

    @POST
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    @RolesAllowed("admin")
    @Path("createauction")
    public String auctionCreator(AuctionDTO auctionDTO) {
        AuctionDTO auctionResponseDTO = null;
        if (auctionDTO != null) {
            try {
                System.out.println(auctionDTO.toString());
                auctionResponseDTO = AUCTION_FACADE.createAuction(auctionDTO);
            } catch (Exception e) {
                throw new NotFoundException("Auction could not be created");
            }
        } else {
            throw new NotFoundException("Missing auction");
        }
        if (auctionResponseDTO != null) {
            return GSON.toJson(auctionResponseDTO);
        } else {
            throw new NotFoundException("auction could not be inserted");

        }
    }
    @POST
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    @Path("createboat")
    public String boatCreator(BoatDTO boatDTO) {
        BoatDTO boatResponseDTO = null;
        if (boatDTO != null) {
            try {
                System.out.println(boatDTO.toString());
                boatResponseDTO = BOAT_FACADE.createNewBoat(boatDTO);
            } catch (Exception e) {
                throw new NotFoundException("Boat could not be created");
            }
        } else {
            throw new NotFoundException("Missing boat");
        }
        if (boatResponseDTO != null) {
            return GSON.toJson(boatResponseDTO);
        } else {
            throw new NotFoundException("Boat could not be inserted");
        }

    }
}

