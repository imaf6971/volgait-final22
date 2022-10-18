package xyz.imaf6971.volgaitfinal.model;

import javax.persistence.*;
import java.util.Set;

import static xyz.imaf6971.volgaitfinal.model.Roles.ADMIN;
import static xyz.imaf6971.volgaitfinal.model.Roles.MODERATOR;

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

    @Column(name = "can_publish", nullable = false)
    private boolean canPublish;

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

    public boolean isModerator() {
        return role.getTitle().equals(MODERATOR.value);
    }

    public void setCanPublish(boolean canPublish) {
        this.canPublish = canPublish;
    }

    public boolean isCanPublish() {
        return canPublish;
    }
}
