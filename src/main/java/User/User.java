package User;

import org.hibernate.annotations.NaturalId;
import javax.persistence.*;

@Entity
@Table(name = "users")
public class User {

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @NaturalId
    @Column(name = "name")
    private String name;

    @NaturalId
    @Column(name = "password")
    private String password;

    @NaturalId
    @Column(name = "role", columnDefinition = "varchar(255) default 'user'")
    private String role;

    public User(int id, String name, String password, String role) {
        this.id = id;
        this.name = name;
        this.password = password;
        this.role = role;
    }

    public User(String name, String password, String role) {
        this.name = name;
        this.password = password;
        this.role = role;
    }

    public User(String name, String password) {
        this.name = name;
        this.password = password;
    }

    public User(){
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String email) {
        this.password = password;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String country) {
        this.role = role;
    }
}
