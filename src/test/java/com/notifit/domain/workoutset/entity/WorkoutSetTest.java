package com.notifit.domain.workoutset.entity;

import com.notifit.domain.workout.entity.Workout;
import com.notifit.domain.workout.entity.enums.BodyPart;
import com.notifit.domain.workoutset.entity.enums.Unit;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.*;

class WorkoutSetTest {

    @Test
    @DisplayName("운동 세트 엔티티를 생성한다.")
    void workoutSetTest() {
        // given
        int setNumber = 1;
        int reps = 5;
        float weight = 100;
        Unit unit = Unit.KG;
        String memo = "TopSet";
        BodyPart bodyPart = BodyPart.BACK;
        String workoutName = "benchPress";
        Workout workout = Workout.of(bodyPart, workoutName);

        // when
        WorkoutSet workoutSet =
                WorkoutSet.of(setNumber, reps, weight, unit, memo, workout);

        // then
        assertThat(workoutSet.getSetNumber()).isEqualTo(setNumber);
        assertThat(workoutSet.getReps()).isEqualTo(reps);
        assertThat(workoutSet.getWeight()).isEqualTo(weight);
        assertThat(workoutSet.getUnit()).isEqualTo(unit);
        assertThat(workoutSet.getMemo()).isEqualTo(memo);
        assertThat(workoutSet.getWorkout()).isEqualTo(workout);
    }
}