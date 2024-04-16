package com.example.realworld.repositories;

import com.example.realworld.models.User;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
  boolean existsByUsername(String username);
  boolean existsByEmail(String email);
  @Modifying
  @Transactional
  @Query(value = """
          INSERT INTO users (email, username, password, bio, image)
          VALUES (:#{#user.email},:#{#user.username},:#{#user.password},:#{#user.bio},:#{#user.image})"""
          ,nativeQuery = true)
  void addUser(@Param("user") User user);
}
