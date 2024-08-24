package com.notifit.entity.workout;

import com.notifit.entity.workout.enums.BodyPart;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Workout {

    @Id
    @GeneratedValue
    @Column(name = "workout_id")
    private Long id;

    @Enumerated(EnumType.STRING)
    private BodyPart bodyPart;
    private String workoutName;

    private Workout(BodyPart bodyPart, String workoutName) {
        this.bodyPart = bodyPart;
        this.workoutName = workoutName;
    }

    public static Workout of(BodyPart bodyPart, String workoutName) {
        return new Workout(bodyPart, workoutName);
    }
}
