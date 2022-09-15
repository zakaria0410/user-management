package com.miniproject.usermanagement.Repository;

import com.miniproject.usermanagement.models.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {
    @Query("select u from User u where u.username = ?1 or u.email = ?2")
    Optional<User> findByUsernameOrEmail(String username, String email);


}
