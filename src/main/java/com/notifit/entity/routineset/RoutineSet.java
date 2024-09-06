package com.notifit.entity.routineset;

import com.notifit.entity.workoutroutine.WorkoutRoutine;
import com.notifit.entity.workoutset.WorkoutSet;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class RoutineSet {

    @Id
    @GeneratedValue
    @Column(name = "routine_set_id")
    private Long id;

    @ManyToOne
    @JoinColumn(name = "workout_routine_id")
    private WorkoutRoutine workoutRoutine;

    @ManyToOne
    @JoinColumn(name = "workout_set_id")
    private WorkoutSet workoutSet;

    private RoutineSet(WorkoutRoutine workoutRoutine, WorkoutSet workoutSet) {
        this.workoutRoutine = workoutRoutine;
        this.workoutSet = workoutSet;
    }

    public RoutineSet of(WorkoutRoutine workoutRoutine, WorkoutSet workoutSet) {
        return new RoutineSet(workoutRoutine,  workoutSet);
    }
}
