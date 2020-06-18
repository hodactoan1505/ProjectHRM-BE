package com.brycen.hrm.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.brycen.hrm.model.ProjectEntity;

public interface ProjectRepository extends JpaRepository<ProjectEntity, Integer>{

}
