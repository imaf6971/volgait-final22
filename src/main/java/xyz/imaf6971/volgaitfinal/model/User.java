package xyz.imaf6971.volgaitfinal.model;

import javax.persistence.*;
import java.util.Set;

import static xyz.imaf6971.volgaitfinal.model.Roles.ADMIN;

@Entity
@Table(name = "users")
public class User extends AbstractEntity {

    @Column(name = "username", nullable = false)
    private String username;

    @Column(name = "password", nullable = false)
    private String password;

    @ManyToOne(optional = false)
    @JoinColumn(name = "role_id", nullable = false)
    private Role role;

    @OneToMany(mappedBy = "author")
    private Set<Advertisement> advertisements;

    public Role getRole() {
        return role;
    }

    public void setRole(Role role) {
        this.role = role;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public boolean isAdmin() {
        return role.getTitle().equals(ADMIN.value);
    }

    public boolean isAuthorOf(Advertisement advertisement) {
        return advertisements.contains(advertisement);
    }

}
