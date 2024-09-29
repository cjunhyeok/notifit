package com.notifit.controller.dtos.workoutplan.create;

import com.notifit.controller.dtos.workout.create.WorkoutRequest;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Getter
@NoArgsConstructor
public class WorkoutPlanRequest {

    private LocalDate scheduleDate;
    List<WorkoutRequest> workoutRequests = new ArrayList<>();
}
