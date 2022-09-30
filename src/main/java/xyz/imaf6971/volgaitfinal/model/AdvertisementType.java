package xyz.imaf6971.volgaitfinal.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "advertisement_types")
public class AdvertisementType extends AbstractEntity {

    @Column(name = "title", nullable = false, unique = true)
    private String title;

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

}
