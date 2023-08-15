package it.firstdocker.Repository;

import it.firstdocker.Entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface CustomerRepository extends JpaRepository<User, Long> {

    boolean existsUserByEmail(String email);

    boolean existsUserById(Long id);

    Optional<User> findUserByEmail(String email);
}
