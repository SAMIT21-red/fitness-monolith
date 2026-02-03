package com.project.fitness.repository;

import com.project.fitness.entities.Activity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@Repository
public interface ActivityRepository extends JpaRepository<Activity , String> {
    List<Activity> findByUserId(String userId);}
