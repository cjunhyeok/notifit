package com.notifit.repository;

import com.notifit.entity.workout.Workout;
import com.notifit.entity.workout.enums.BodyPart;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import static org.assertj.core.api.Assertions.*;

@Transactional
@SpringBootTest
class WorkoutRepositoryTest {

    @Autowired
    private WorkoutRepository workoutRepository;

    @Test
    @DisplayName("운동 엔티티를 저장한다.")
    void saveTest() {
        // given
        BodyPart bodyPart = BodyPart.CHEST;
        String workoutName = "BenchPress";
        Workout workout = Workout.of(bodyPart, workoutName);

        // when
        Workout savedWorkout = workoutRepository.save(workout);

        // then
        assertThat(savedWorkout.getId()).isNotNull();
        assertThat(savedWorkout.getBodyPart()).isEqualTo(bodyPart);
        assertThat(savedWorkout.getWorkoutName()).isEqualTo(workoutName);
    }
}