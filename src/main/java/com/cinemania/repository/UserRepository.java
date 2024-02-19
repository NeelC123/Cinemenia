package com.cinemania.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.cinemania.model.CinemaniaUser;



@Repository
public interface UserRepository extends JpaRepository<CinemaniaUser, Long> {
CinemaniaUser  findByUserEmail(String userEmail);
CinemaniaUser findByUserEmailAndPassword(String userEmail, String password);
}
