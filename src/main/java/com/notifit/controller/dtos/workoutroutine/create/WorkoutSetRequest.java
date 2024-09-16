package com.notifit.controller.dtos.workoutroutine.create;

import com.notifit.entity.workoutset.enums.Unit;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class WorkoutSetRequest {

    private int setNumber;
    private int reps;
    private float weight;
    private Unit unit;
    private String memo;
}
