package xyz.imaf6971.volgaitfinal.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import xyz.imaf6971.volgaitfinal.model.Advertisement;

public interface AdvertisementRepository extends JpaRepository<Advertisement, Long> {
}
