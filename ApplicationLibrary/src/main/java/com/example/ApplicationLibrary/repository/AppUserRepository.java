package com.example.ApplicationLibrary.repository;

import com.example.ApplicationLibrary.models.AppUser;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AppUserRepository extends JpaRepository<AppUser, Integer> {

    AppUser findByEmail(String email);
    AppUser findByUsername(String username);



}
