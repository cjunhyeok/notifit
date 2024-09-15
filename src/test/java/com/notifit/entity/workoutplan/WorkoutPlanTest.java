package com.notifit.entity.workoutplan;

import com.notifit.entity.member.Member;
import com.notifit.entity.workout.Workout;
import com.notifit.entity.workout.enums.BodyPart;
import com.notifit.entity.workoutset.WorkoutSet;
import com.notifit.entity.workoutset.enums.Unit;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.time.Duration;
import java.time.LocalDate;

import static org.assertj.core.api.Assertions.*;

class WorkoutPlanTest {

    @Test
    @DisplayName("운동 계획 엔티티를 생성한다.")
    void workoutPlanTest() {
        // given
        LocalDate scheduleDate = LocalDate.now();
        boolean isCompleted = false;
        Duration elapsedTime = Duration.ofHours(1);

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
        WorkoutPlan workoutPlan = WorkoutPlan.of(scheduleDate, isCompleted, elapsedTime, member);

        // then
        assertThat(workoutPlan.getScheduleDate()).isEqualTo(scheduleDate);
        assertThat(workoutPlan.isCompleted()).isFalse();
        assertThat(workoutPlan.getElapsedTime()).isEqualTo(elapsedTime);
        assertThat(workoutPlan.getMember()).isEqualTo(member);
    }
}