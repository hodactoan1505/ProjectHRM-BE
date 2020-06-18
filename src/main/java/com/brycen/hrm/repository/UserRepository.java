package com.brycen.hrm.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.brycen.hrm.model.UserEntity;

public interface UserRepository extends JpaRepository<UserEntity, String>{
    UserEntity findByUsername(String username);
}
