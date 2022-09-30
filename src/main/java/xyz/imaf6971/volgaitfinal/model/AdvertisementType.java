package xyz.imaf6971.volgaitfinal.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import java.util.Set;

@Entity
@Table(name = "advertisement_types")
public class AdvertisementType extends AbstractEntity {

    @Column(name = "title", nullable = false, unique = true)
    private String title;

    @OneToMany(mappedBy = "type")
    private Set<Advertisement> advertisements;

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Set<Advertisement> getAdvertisements() {
        return advertisements;
    }

    public void setAdvertisements(Set<Advertisement> advertisements) {
        this.advertisements = advertisements;
    }
}
