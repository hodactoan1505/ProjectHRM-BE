package com.brycen.hrm.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.brycen.hrm.model.RoleEntity;

public interface RoleRepository extends JpaRepository<RoleEntity, Integer>{

}
