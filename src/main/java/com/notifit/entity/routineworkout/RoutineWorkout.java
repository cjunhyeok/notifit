package com.notifit.entity.routineworkout;

import com.notifit.entity.workout.Workout;
import com.notifit.entity.workoutroutine.WorkoutRoutine;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class RoutineWorkout {

    @Id
    @GeneratedValue
    @Column(name = "routine_set_id")
    private Long id;

    @ManyToOne
    @JoinColumn(name = "workout_routine_id")
    private WorkoutRoutine workoutRoutine;

    @ManyToOne
    @JoinColumn(name = "workout_id")
    private Workout workout;

    private RoutineWorkout(WorkoutRoutine workoutRoutine, Workout workout) {
        this.workoutRoutine = workoutRoutine;
        this.workout = workout;
    }

    public static RoutineWorkout of(WorkoutRoutine workoutRoutine, Workout workout) {
        return new RoutineWorkout(workoutRoutine,  workout);
    }
}
