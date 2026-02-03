package com.project.fitness.repository;

import com.project.fitness.entities.Activity;
import com.project.fitness.entities.Recommendation;
import com.project.fitness.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface RecommendationRepository extends JpaRepository<Recommendation,String> {
    List<Recommendation> findByUser(User user);
    List<Recommendation> findByActivityId(String activityId);
}
