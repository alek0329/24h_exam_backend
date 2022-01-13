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

    Owner user = new Owner("user", "kode123", "user","N/A","12345678");
    Owner admin = new Owner("admin", "kode123","user","N/A","12345678");
    Owner both = new Owner("user_admin", "kode123","user","N/A","12345678");
    Owner alek = new Owner ("alek","123","Aleksander Rolander", "læssøesgade 14A, 1tv","26397400");
    Owner bo  = new Owner ("Bo","123","Bo Bådløs", "ingen Båd gade 4","23487500");
    Owner jens = new Owner ("Jens","123","Jens Peter","Lars Allan Vej 24","96374231");
    Owner hans = new Owner("Hans","123","Hans Hansen","Hansensgade 12","25528524");
    Owner ditlev = new Owner("Ditlev","123","Ditlev Hansen","Hansensgade 12","27398400");
    Owner mario = new Owner("Mario","123","Mario Luigi","Læssøesgade 12","26397400");
    Owner lisbeth = new Owner("Lisbeth","123","Lisbeth Hyggesen","Borgmestergade 44","26397400");
    Auction virum = new Auction("Salg af Luksusbåde","20/04/22","15:30","Virum");
    Auction cph = new Auction("Politi Auction","11/03/22","12:00","København");
    Boat jollen = new Boat("Henriette","Suzuki",1980,"www.pictureofboat.com");
    Boat yachten = new Boat("Line","Yacht",2010,"www.pictureofboat.com");
    Boat fiskeBåd = new Boat("Tunklatten", "Fiat", 2017,"www.pictureofboat.com");
    Boat yacht1= new Boat("Niels Peter", "Yacht", 2020,"www.pictureofboat.com");
    Boat minYacht = new Boat("Den Sorte Perle", "Yacht", 1310,"www.pictureofboat.com");
    if(admin.getUserPass().equals("test")||user.getUserPass().equals("test")||both.getUserPass().equals("test"))
      throw new UnsupportedOperationException("You have not changed the passwords");

    em.getTransaction().begin();
    Role userRole = new Role("user");
    Role adminRole = new Role("admin");
    user.addRole(userRole);
    admin.addRole(adminRole);
    both.addRole(userRole);
    both.addRole(adminRole);

    bo.addRole(userRole);
    lisbeth.addRole(userRole);
    mario.addRole(userRole);
    hans.addRole(userRole);
    jens.addRole(userRole);
    ditlev.addRole(userRole);
    alek.addRole(adminRole);
    jens.addBoat(yacht1);
    alek.addBoat(minYacht);
    ditlev.addBoat(jollen);
    mario.addBoat(yachten);
    hans.addBoat(fiskeBåd);
    lisbeth.addBoat(yachten);
    hans.addBoat(jollen);
    jollen.setAuction(virum);
    fiskeBåd.setAuction(cph);
    yachten.setAuction(cph);
    minYacht.setAuction(cph);
    em.persist(alek);
    em.persist(minYacht);
    em.persist(ditlev);
    em.persist(jens);
    em.persist(bo);
    em.persist(hans);
    em.persist(mario);
    em.persist(lisbeth);
    em.persist(cph);
    em.persist(virum);
    em.persist(jollen);
    em.persist(yacht1);
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