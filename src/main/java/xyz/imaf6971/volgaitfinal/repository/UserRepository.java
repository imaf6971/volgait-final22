package xyz.imaf6971.volgaitfinal.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import xyz.imaf6971.volgaitfinal.model.User;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {
    Optional<User> findByUsername(String username);

    void deleteByUsername(String username);
}
