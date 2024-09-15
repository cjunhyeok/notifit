package com.notifit.entity.planworkout;

import com.notifit.entity.workout.Workout;
import com.notifit.entity.workoutplan.WorkoutPlan;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class PlanWorkout {

    @Id
    @GeneratedValue
    @Column(name = "plan_set_id")
    private Long id;

    @ManyToOne
    @JoinColumn(name = "workout_plan_id")
    private WorkoutPlan workoutPlan;

    @ManyToOne
    @JoinColumn(name = "workout_id")
    private Workout workout;

    private PlanWorkout(WorkoutPlan workoutPlan, Workout workout) {
        this.workoutPlan = workoutPlan;
        this.workout = workout;
    }

    public static PlanWorkout of(WorkoutPlan workoutPlan, Workout workout) {
        return new PlanWorkout(workoutPlan,  workout);
    }
}
