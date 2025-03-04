package com.unitau.tgvinicius.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.unitau.tgvinicius.entities.User;

public interface UserRepository extends JpaRepository<User, String> {

}
