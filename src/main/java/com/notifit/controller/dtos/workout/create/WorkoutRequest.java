package com.notifit.controller.dtos.workout.create;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Getter
@NoArgsConstructor
public class WorkoutRequest {

    private Long workoutId;
    List<WorkoutSetRequest> workoutSetRequests = new ArrayList<>();

    @Builder
    public WorkoutRequest(Long workoutId, List<WorkoutSetRequest> workoutSetRequests) {
        this.workoutId = workoutId;
        this.workoutSetRequests = workoutSetRequests;
    }
}
