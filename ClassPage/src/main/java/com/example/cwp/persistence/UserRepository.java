package com.example.cwp.persistence;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.cwp.domain.User;

public interface UserRepository extends JpaRepository<User, Integer>{

}
