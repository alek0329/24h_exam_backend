package facades;

import DTO.UserDTOS.CreateUserDTO;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import entities.Owner;
import entities.Role;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;

import security.errorhandling.AuthenticationException;

/**
 * @author lam@cphbusiness.dk
 */
public class UserFacade {

    private static EntityManagerFactory emf;
    private static UserFacade instance;
    private Gson gson = new GsonBuilder().setPrettyPrinting().create();

    private UserFacade() {
    }

    /**
     * @param _emf
     * @return the instance of this facade.
     */
    public static UserFacade getUserFacade(EntityManagerFactory _emf) {
        if (instance == null) {
            emf = _emf;
            instance = new UserFacade();
        }
        return instance;
    }

    public Owner getVerifiedUser(String userName, String password) throws AuthenticationException {
        EntityManager em = emf.createEntityManager();
        Owner owner;
        try {
            owner = em.find(Owner.class, userName);
            if (owner == null || !owner.verifyPassword(password, owner.getUserPass())) {
                throw new AuthenticationException("Invalid user name or password");
            }
        } finally {
            em.close();
        }
        return owner;
    }

    public String createUser(String username, String password) {
        CreateUserDTO createUserDTO = new CreateUserDTO();
        EntityManager em = emf.createEntityManager();

        Owner owner = new Owner("username", "password","name","address","phone");
        Role userRole;
        try {
            if (em.find(Role.class, "user") != null) {
                em.getTransaction().begin();
                userRole = em.find(Role.class, "user");
                owner.addRole(userRole);
                em.persist(owner);
                em.getTransaction().commit();
            }
            else {
                Role newUserRole = new Role("user");
                em.getTransaction().begin();
                em.persist(newUserRole);
                owner.addRole(newUserRole);
                em.persist(owner);
                em.getTransaction().commit();
            }


        } catch (Exception e) {
            createUserDTO.setStatus("failed");
            createUserDTO.setMessage(username + " already exists!");
            return gson.toJson(createUserDTO);
        } finally
        {
            em.close();
        }
        createUserDTO.setStatus("success");
        createUserDTO.setMessage(username + " created successfully!");
        return gson.toJson(createUserDTO);
    }


}
