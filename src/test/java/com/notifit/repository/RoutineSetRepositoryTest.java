package com.notifit.repository;

import com.notifit.entity.member.Member;
import com.notifit.entity.routineset.RoutineSet;
import com.notifit.entity.workout.Workout;
import com.notifit.entity.workout.enums.BodyPart;
import com.notifit.entity.workoutroutine.WorkoutRoutine;
import com.notifit.entity.workoutset.WorkoutSet;
import com.notifit.entity.workoutset.enums.Unit;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;

import static org.assertj.core.api.Assertions.*;

@Transactional
@SpringBootTest
class RoutineSetRepositoryTest {

    @Autowired
    private RoutineSetRepository routineSetRepository;
    @Autowired
    private MemberRepository memberRepository;
    @Autowired
    private WorkoutRoutineRepository workoutRoutineRepository;
    @Autowired
    private WorkoutRepository workoutRepository;
    @Autowired
    private WorkoutSetRepository workoutSetRepository;

    @Test
    @DisplayName("운동루틴 운동세트 다대다 테이블 엔티티를 저장한다.")
    void saveTest() {
        String username = "username";
        Member member = createMember(username);
        Member savedMember = memberRepository.save(member);

        String routineName = "chestDay";
        LocalDateTime lastWorkoutTime = LocalDateTime.now();
        WorkoutRoutine workoutRoutine = WorkoutRoutine.of(routineName, lastWorkoutTime, savedMember);
        WorkoutRoutine savedWorkoutRoutine = workoutRoutineRepository.save(workoutRoutine);

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
        WorkoutSet savedWorkoutSet = workoutSetRepository.save(workoutSet);

        RoutineSet routineSet = RoutineSet.of(savedWorkoutRoutine, savedWorkoutSet);

        // when
        RoutineSet savedRoutineSet = routineSetRepository.save(routineSet);

        // then
        assertThat(savedRoutineSet.getId()).isNotNull();
        assertThat(savedRoutineSet.getWorkoutRoutine()).isEqualTo(workoutRoutine);
        assertThat(savedRoutineSet.getWorkoutSet()).isEqualTo(workoutSet);
    }

    private Member createMember(String username) {
        return Member.of(username, "password", "name", "phoneNumber", true, true);
    }
}