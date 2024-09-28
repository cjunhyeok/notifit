package com.notifit.controller.dtos.workoutroutine.create;

import com.notifit.controller.dtos.workout.create.WorkoutRequest;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Getter
@NoArgsConstructor
public class WorkoutRoutineRequest {

    private String routineName;
    List<WorkoutRequest> workoutRequests = new ArrayList<>();

    @Builder
    public WorkoutRoutineRequest(String routineName, List<WorkoutRequest> workoutRequests) {
        this.routineName = routineName;
        this.workoutRequests = workoutRequests;
    }
}
