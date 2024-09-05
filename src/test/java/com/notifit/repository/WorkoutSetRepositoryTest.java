package com.notifit.repository;

import com.notifit.entity.workout.Workout;
import com.notifit.entity.workout.enums.BodyPart;
import com.notifit.entity.workoutset.WorkoutSet;
import com.notifit.entity.workoutset.enums.Unit;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import static org.assertj.core.api.Assertions.*;

@Transactional
@SpringBootTest
class WorkoutSetRepositoryTest {

    @Autowired
    private WorkoutSetRepository workoutSetRepository;
    @Autowired
    private WorkoutRepository workoutRepository;

    @Test
    @DisplayName("운동세트 엔티티를 저장한다.")
    void saveTest() {
        // given
        BodyPart bodyPart = BodyPart.CHEST;
        String workoutName = "BenchPress";
        Workout workout = Workout.of(bodyPart, workoutName);
        Workout savedWorkout = workoutRepository.save(workout);

        int setNumber = 1;
        int reps = 8;
        float weight = 100;
        Unit unit = Unit.KG;
        String memo = "topSet";
        WorkoutSet workoutSet = WorkoutSet.of(setNumber, reps, weight, unit, memo, savedWorkout);

        // when
        WorkoutSet savedWorkoutSet = workoutSetRepository.save(workoutSet);

        // then
        assertThat(savedWorkoutSet.getId()).isNotNull();
        assertThat(savedWorkoutSet.getSetNumber()).isEqualTo(setNumber);
        assertThat(savedWorkoutSet.getReps()).isEqualTo(reps);
        assertThat(savedWorkoutSet.getWeight()).isEqualTo(weight);
        assertThat(savedWorkoutSet.getUnit()).isEqualTo(unit);
        assertThat(savedWorkoutSet.getMemo()).isEqualTo(memo);
        assertThat(savedWorkoutSet.getWorkout()).isEqualTo(workout);
    }
}