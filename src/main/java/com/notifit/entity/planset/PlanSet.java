package com.notifit.entity.planset;

import com.notifit.entity.workoutplan.WorkoutPlan;
import com.notifit.entity.workoutset.WorkoutSet;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class PlanSet {

    @Id
    @GeneratedValue
    @Column(name = "plan_set_id")
    private Long id;

    @ManyToOne
    @JoinColumn(name = "workout_plan_id")
    private WorkoutPlan workoutPlan;

    @ManyToOne
    @JoinColumn(name = "workout_set_id")
    private WorkoutSet workoutSet;

    private PlanSet(WorkoutPlan workoutPlan, WorkoutSet workoutSet) {
        this.workoutPlan = workoutPlan;
        this.workoutSet = workoutSet;
    }

    public PlanSet of(WorkoutPlan workoutPlan, WorkoutSet workoutSet) {
        return new PlanSet(workoutPlan,  workoutSet);
    }
}
