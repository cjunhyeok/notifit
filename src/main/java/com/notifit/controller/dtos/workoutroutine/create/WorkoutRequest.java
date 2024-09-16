package com.notifit.controller.dtos.workoutroutine.create;

import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;

@Getter
@NoArgsConstructor
public class WorkoutRequest {

    private Long workoutId;
    List<WorkoutSetRequest> workoutSetRequests;
}
