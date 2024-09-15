package com.notifit.repository;

import com.notifit.entity.member.Member;
import com.notifit.entity.planworkout.PlanWorkout;
import com.notifit.entity.workout.Workout;
import com.notifit.entity.workout.enums.BodyPart;
import com.notifit.entity.workoutplan.WorkoutPlan;
import com.notifit.entity.workoutset.WorkoutSet;
import com.notifit.entity.workoutset.enums.Unit;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import java.time.Duration;
import java.time.LocalDate;

import static org.assertj.core.api.Assertions.*;

@Transactional
@SpringBootTest
class PlanWorkoutRepositoryTest {

    @Autowired
    private PlanWorkoutRepository planWorkoutRepository;
    @Autowired
    private MemberRepository memberRepository;
    @Autowired
    private WorkoutPlanRepository workoutPlanRepository;
    @Autowired
    private WorkoutRepository workoutRepository;
    @Autowired
    private WorkoutSetRepository workoutSetRepository;

    @Test
    @DisplayName("운동계획 운동세트 다대다 테이블 엔티티를 저장한다.")
    void saveTest() {
        LocalDate scheduleDate = LocalDate.now();
        boolean isComplete = false;
        Duration elapsedTime = Duration.ofSeconds(90);
        String username = "username";
        Member member = createMember(username);
        Member savedMember = memberRepository.save(member);
        WorkoutPlan workoutPlan = WorkoutPlan.of(scheduleDate, isComplete, elapsedTime, savedMember);
        WorkoutPlan savedWorkoutPlan = workoutPlanRepository.save(workoutPlan);

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
        workoutSetRepository.save(workoutSet);

        PlanWorkout planWorkout = PlanWorkout.of(savedWorkoutPlan, savedWorkout);

        // when
        PlanWorkout savedPlanWorkout = planWorkoutRepository.save(planWorkout);

        // then
        assertThat(savedPlanWorkout.getId()).isNotNull();
        assertThat(savedPlanWorkout.getWorkoutPlan()).isEqualTo(workoutPlan);
        assertThat(savedPlanWorkout.getWorkout()).isEqualTo(savedWorkout);
    }

    private Member createMember(String username) {
        return Member.of(username, "password", "name", "phoneNumber", true, true);
    }
}