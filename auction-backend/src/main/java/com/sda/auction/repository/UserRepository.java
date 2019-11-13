package com.sda.auction.repository;

import com.sda.auction.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Integer> { // user e obiectul iar Integer e id-ul
     User findByEmail(String email);
}
