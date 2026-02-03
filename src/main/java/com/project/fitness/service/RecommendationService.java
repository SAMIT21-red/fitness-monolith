package com.project.fitness.service;

import com.project.fitness.dto.RecommendationRequest;
import com.project.fitness.entities.Activity;
import com.project.fitness.entities.Recommendation;
import com.project.fitness.entities.User;
import com.project.fitness.repository.ActivityRepository;
import com.project.fitness.repository.RecommendationRepository;
import com.project.fitness.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class RecommendationService {

    private final UserRepository userRepository;
    private final ActivityRepository activityRepository;
    private final RecommendationRepository recommendationRepository;


    public Recommendation generateRecommendation(RecommendationRequest request) {
        User user =userRepository.findById(request.getUserId())
                .orElseThrow(()-> new RuntimeException("User Not Found"+request.getUserId()));

        Activity activity  =activityRepository.findById(request.getActivityId())
                .orElseThrow(()-> new RuntimeException("Activity Not Found"+request.getActivityId()));

        Recommendation recommendation =Recommendation.builder()
                 .user(user)
                 .activity(activity)
                .improvements(request.getImprovements())
                .suggestions(request.getSuggestions())
                .safety(request.getSafety())
                .build();

        return recommendationRepository.save(recommendation);
    }

    public List<Recommendation> getUserRecommendation(String userId) {

        User user = userRepository.findById(userId)
                .orElseThrow(() -> new RuntimeException("User not found"));

        return recommendationRepository.findByUser(user);
    }

    public  List<Recommendation> getActivityRecommendation(String activityId) {

//        Activity activity = activityRepository.findById(activityId)
//                .orElseThrow(()-> new RuntimeException("Activity not found"));

        return recommendationRepository.findByActivityId(activityId);
    }
}
