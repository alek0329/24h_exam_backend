package entities;

import org.mindrot.jbcrypt.BCrypt;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name="Owner")
public class Owner implements Serializable {
    private static final long serialVersionUID = 1L;


    @Column(name = "ownerID")
    @NotNull
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    private int ownerId;

    @Id
    @Basic(optional = false)
    @NotNull
    @Column(name = "user_name", length = 25)
    private String userName;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 255)
    @Column(name = "user_pass")
    private String userPass;

    @Column (name ="name")
    private String name;
    @Column (name = "address")
    private String address;
    @Column (name = "phone")
    private String phone;

    @JoinTable(name = "owner_roles", joinColumns = {
            @JoinColumn(name = "user_name", referencedColumnName = "user_name")}, inverseJoinColumns = {
            @JoinColumn(name = "role_name", referencedColumnName = "role_name")})
    @ManyToMany
    private List<Role> roleList = new ArrayList<>();

    @ManyToMany(mappedBy = "owners",cascade = CascadeType.PERSIST)
    private List<Boat> boats;

    public Owner(String userName, String userPass, String name, String address, String phone) {
        this.ownerId = ownerId;
        this.userName = userName;
        String salt =BCrypt.gensalt();
        this.userPass = BCrypt.hashpw(userPass, salt);

        this.name = name;
        this.address = address;
        this.phone = phone;
        this.boats = new ArrayList<>();
    }

    public List<String> getRolesAsStrings() {
        if (roleList.isEmpty()) {
            return null;
        }
        List<String> rolesAsStrings = new ArrayList<>();
        roleList.forEach((role) -> {
            rolesAsStrings.add(role.getRoleName());
        });
        return rolesAsStrings;
    }

    public Owner() {
    }


    public boolean verifyPassword(String pw, String hashedPw){
        return BCrypt.checkpw(pw, hashedPw);
    }

    public void addBoat(Boat boat) {
        if (boat != null) {
            this.boats.add(boat);
            boat.getOwners().add(this);
        }
    }


    public int getOwnerId() {
        return ownerId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public List<Boat> getBoats() {
        return boats;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getUserPass() {
        return this.userPass;
    }

    public void setUserPass(String userPass) {
        this.userPass = userPass;
    }

    public List<Role> getRoleList() {
        return roleList;
    }

    public void setRoleList(List<Role> roleList) {
        this.roleList = roleList;
    }

    public void addRole(Role userRole) {
        roleList.add(userRole);
    }

}