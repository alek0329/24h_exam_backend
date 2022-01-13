package utils;


import entities.*;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import java.util.ArrayList;
import java.util.List;

public class SetupTestUsers {

  public static void main(String[] args) {
    setupTestUsers();
  }

  public static void setupTestUsers(){
    EntityManagerFactory emf = EMF_Creator.createEntityManagerFactory();
    EntityManager em = emf.createEntityManager();

    // IMPORTAAAAAAAAAANT!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!
    // This breaks one of the MOST fundamental security rules in that it ships with default users and passwords
    // CHANGE the three passwords below, before you uncomment and execute the code below
    // Also, either delete this file, when users are created or rename and add to .gitignore
    // Whatever you do DO NOT COMMIT and PUSH with the real passwords

    User user = new User("user", "kode123");
    User admin = new User("admin", "kode123");
    User both = new User("user_admin", "kode123");

    Owner hans = new Owner("Hans Hansen","Hansensgade 12","25528524");
    Owner ditlev = new Owner("Ditlev Hansen","Hansensgade 12","27398400");
    Owner mario = new Owner("Mario Luigi","Læssøesgade 12","26397400");
    Owner lisbeth = new Owner("Lisbeth Hyggesen","Borgmestergade 44","26397400");
    Auction virum = new Auction("Salg af Luksusbåde","20/04/22","15:30","Virum");
    Auction cph = new Auction("Politi Auction","11/03/22","12:00","København");
    Boat jollen = new Boat("Henriette","Suzuki",1980,"www.pictureofboat.com");
    Boat yachten = new Boat("Line","Yacht",2010,"www.pictureofboat.com");
    Boat fiskeBåd = new Boat("Tunklatten", "Fiat", 2017,"www.pictureofboat.com");

    if(admin.getUserPass().equals("test")||user.getUserPass().equals("test")||both.getUserPass().equals("test"))
      throw new UnsupportedOperationException("You have not changed the passwords");

    em.getTransaction().begin();
    Role userRole = new Role("user");
    Role adminRole = new Role("admin");
    user.addRole(userRole);
    admin.addRole(adminRole);
    both.addRole(userRole);
    both.addRole(adminRole);

    ditlev.addBoat(jollen);
    mario.addBoat(yachten);
    hans.addBoat(fiskeBåd);
    lisbeth.addBoat(yachten);
    hans.addBoat(jollen);
    jollen.setAuction(virum);
    fiskeBåd.setAuction(cph);
    yachten.setAuction(cph);
    em.persist(ditlev);
    em.persist(hans);
    em.persist(mario);
    em.persist(lisbeth);
    em.persist(cph);
    em.persist(virum);
    em.persist(jollen);
    em.persist(fiskeBåd);
    em.persist(yachten);

    em.persist(userRole);
    em.persist(adminRole);
    em.persist(user);
    em.persist(admin);
    em.persist(both);
    em.getTransaction().commit();
    //System.out.println("PW: " + user.getUserPass());
    //System.out.println("Testing user with OK password: " + user.verifyPassword("Kodener123",user.getUserPass()));
    //System.out.println("Testing user with wrong password: " + user.verifyPassword("test1"));
    //System.out.println("Created TEST Users");
  }

}