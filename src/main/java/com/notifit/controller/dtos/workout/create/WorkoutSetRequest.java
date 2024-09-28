package com.notifit.controller.dtos.workout.create;

import com.notifit.entity.workoutset.enums.Unit;
import lombok.Builder;
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

    @Builder
    public WorkoutSetRequest(int setNumber, int reps, float weight, Unit unit, String memo) {
        this.setNumber = setNumber;
        this.reps = reps;
        this.weight = weight;
        this.unit = unit;
        this.memo = memo;
    }
}
