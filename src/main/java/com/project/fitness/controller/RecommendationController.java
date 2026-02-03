package com.project.fitness.controller;

import com.project.fitness.dto.RecommendationRequest;
import com.project.fitness.entities.Recommendation;
import com.project.fitness.service.RecommendationService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/recommendation")
@RequiredArgsConstructor
public class RecommendationController {

    private final RecommendationService recommendationService;

    // POST → generate recommendation
    @PostMapping("/generate")
    public ResponseEntity<Recommendation> generateRecommendation(
            @RequestBody RecommendationRequest request
    ) {
        Recommendation recommendation =
                recommendationService.generateRecommendation(request);
        return ResponseEntity.ok(recommendation);
    }

    // GET → fetch user recommendations
    @GetMapping("/user/{userId}")
    public ResponseEntity<List<Recommendation>> getUserRecommendation(@PathVariable String userId) {
        return ResponseEntity.ok(
                recommendationService.getUserRecommendation(userId)
        );
    }

    @GetMapping("/activity/{activityId}")
    public ResponseEntity<List<Recommendation>> getAcivityRecommendation(@PathVariable String activityId) {
        List<Recommendation> recommendationList= recommendationService.getActivityRecommendation(activityId);
        return ResponseEntity.ok(recommendationList);
    }
}
