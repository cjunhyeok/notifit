package com.notifit.entity.workoutroutine;

import com.notifit.entity.member.Member;
import com.notifit.entity.workout.Workout;
import com.notifit.entity.workout.enums.BodyPart;
import com.notifit.entity.workoutset.WorkoutSet;
import com.notifit.entity.workoutset.enums.Unit;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;

import static org.assertj.core.api.Assertions.*;

class WorkoutRoutineTest {

    @Test
    @DisplayName("운동루틴 엔티티를 생성한다")
    void workoutRoutineTest() {
        // given
        String routineName = "ChestDay";
        LocalDateTime lastWorkoutTime = LocalDateTime.now();

        String username = "username";
        String password = "password";
        String name = "name";
        String phoneNumber = "phoneNumber";
        Member member = Member.of(username, password, name, phoneNumber, true, true);

        BodyPart bodyPart = BodyPart.BACK;
        String workoutName = "benchPress";
        Workout workout = Workout.of(bodyPart, workoutName);

        int setNumber = 1;
        int reps = 5;
        float weight = 100;
        Unit unit = Unit.KG;
        String memo = "TopSet";
        WorkoutSet workoutSet =
                WorkoutSet.of(setNumber, reps, weight, unit, memo, workout);


        // when
        WorkoutRoutine workoutRoutine = WorkoutRoutine.of(routineName, lastWorkoutTime, member, workoutSet);

        // then
        assertThat(workoutRoutine.getRoutineName()).isEqualTo(routineName);
        assertThat(workoutRoutine.getLastWorkoutTime()).isEqualTo(lastWorkoutTime);
        assertThat(workoutRoutine.getMember()).isEqualTo(member);
        assertThat(workoutRoutine.getWorkoutSet()).isEqualTo(workoutSet);
    }

}