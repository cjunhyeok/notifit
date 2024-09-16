package com.notifit.controller.dtos.workoutroutine.create;

import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Getter
@NoArgsConstructor
public class WorkoutRoutineRequest {

    private String routineName;
    List<WorkoutRequest> workoutRequests = new ArrayList<>();
}
