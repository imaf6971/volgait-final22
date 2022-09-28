package xyz.imaf6971.volgaitfinal.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import xyz.imaf6971.volgaitfinal.model.Role;

import java.util.Optional;

public interface RoleRepository extends JpaRepository<Role, Long> {

    Optional<Role> findByTitle(String title);

}
