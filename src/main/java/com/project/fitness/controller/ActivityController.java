package com.project.fitness.controller;

import com.project.fitness.dto.ActivityRequest;
import com.project.fitness.dto.ActivityResponse;
import com.project.fitness.service.ActivityService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;
import org.springframework.security.core.Authentication;
import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/activities")
public class ActivityController {

    private final ActivityService activityService;

    @PostMapping
    public ResponseEntity<ActivityResponse> trackActivity(
            @Valid @RequestBody ActivityRequest request,
            @AuthenticationPrincipal UserDetails userDetails
    ) {
        // This is clean and safe. userDetails is injected automatically by Spring.
        return ResponseEntity.ok(activityService.trackActivity(request, userDetails.getUsername()));
    }

    @GetMapping("/my-activities")
    public ResponseEntity<List<ActivityResponse>> getMyActivities(
            @AuthenticationPrincipal UserDetails userDetails
    ) {
        // Find activities by the email of the person currently logged in
        return ResponseEntity.ok(activityService.getActivitiesByEmail(userDetails.getUsername()));
    }
}
