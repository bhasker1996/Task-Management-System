package org.example.taskmanagementsystem.Repository;

import org.example.taskmanagementsystem.Entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {

    //User findByUsername(String Username);
    Optional<User> findByUsername(String username);
    Boolean existsByUsername(String username);

}
