package facades;

import DTO.AuctionDTO;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import entities.Auction;
import entities.Boat;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.TypedQuery;

import java.util.List;


public class AuctionFacade {

    private static EntityManagerFactory emf;
    private static AuctionFacade instance;
    private Gson GSON = new GsonBuilder().setPrettyPrinting().create();

    public AuctionFacade() {
    }

    public static AuctionFacade getAuctionFacade(EntityManagerFactory _emf) {
        if (instance == null) {
            emf = _emf;
            instance = new AuctionFacade();
        }
        return instance;
    }

    public List<AuctionDTO> getAllAuctions() {
        EntityManager em = emf.createEntityManager();
        List <Auction> allAuctions;

        try{
            TypedQuery <Auction> tq = em.createQuery("SELECT a FROM Auction a", Auction.class);
            allAuctions = tq.getResultList();
        }finally {
            em.close();
        }
        return AuctionDTO.getDTO(allAuctions);
    }

    public AuctionDTO removeBoat (int boatId,int auctionId) {
        EntityManager em = emf.createEntityManager();
        Boat boatToRemove;
        Auction auction = null;
        try{
            em.getTransaction().begin();
            boatToRemove = em.find(Boat.class, boatId);
            auction = em.find(Auction.class, auctionId);
            auction.boatRemover(boatToRemove);
            boatToRemove.removeFromAuction(boatToRemove);
            em.persist(auction);
            em.persist(boatToRemove);
            em.getTransaction().commit();
        }finally{
            em.close();
        }
        return new AuctionDTO(auction);
    }
}

