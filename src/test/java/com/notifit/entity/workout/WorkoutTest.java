package com.notifit.entity.workout;

import com.notifit.entity.workout.enums.BodyPart;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.*;

class WorkoutTest {

    @Test
    @DisplayName("운동 엔티티를 생성한다.")
    void workoutTest() {
        // given
        BodyPart bodyPart = BodyPart.CHEST;
        String workoutName = "benchPress";

        // when
        Workout workout = Workout.of(bodyPart, workoutName);

        // then
        assertThat(workout.getBodyPart()).isEqualTo(bodyPart);
        assertThat(workout.getWorkoutName()).isEqualTo(workoutName);
    }
}