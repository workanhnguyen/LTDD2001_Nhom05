package com.example.backend.repositories;

import com.example.backend.models.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User, Integer> {
    @Query("SELECT u FROM User u WHERE u.id=?1")
    User findByUserId(Integer userId);
    @Query("SELECT u FROM User u WHERE u.username LIKE ?1")
    User findByUsername(String username);
}
