package com.notifit.domain.workoutset.entity;

import com.notifit.domain.workout.entity.Workout;
import com.notifit.domain.workoutset.entity.enums.Unit;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class WorkoutSet {

    @Id
    @GeneratedValue
    @Column(name = "workout_detail_id")
    private Long id;

    private int setNumber;
    private int reps;
    private float weight;
    private Unit unit;
    private String memo;

    @JoinColumn(name = "workout_id")
    @ManyToOne(fetch = FetchType.LAZY)
    private Workout workout;

    private WorkoutSet(int setNumber, int reps, float weight, Unit unit, String memo, Workout workout) {
        this.setNumber = setNumber;
        this.reps = reps;
        this.weight = weight;
        this.unit = unit;
        this.memo = memo;
        this.workout = workout;
    }

    public static WorkoutSet of(int setNumber, int reps, float weight, Unit unit, String memo, Workout workout) {
        return new WorkoutSet(
                setNumber,
                reps,
                weight,
                unit,
                memo,
                workout
        );
    }
}
