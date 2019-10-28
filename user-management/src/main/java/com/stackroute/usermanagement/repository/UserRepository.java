package com.stackroute.usermanagement.repository;

import com.stackroute.usermanagement.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {

    @Query("SELECT m FROM User  m WHERE m.username = :username")
    User findByUsername(@Param("username") String username);
}
