package com.example.cslink.user.model.dataccess.dao;

import com.example.cslink.user.model.entity.UserProfile;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserProfileDao extends JpaRepository<UserProfile, Long> {
    Optional<UserProfile> findByUsername(String username);
}

