package com.notifit.service;

import com.notifit.controller.dtos.workout.create.WorkoutRequest;
import com.notifit.controller.dtos.workout.create.WorkoutSetRequest;
import com.notifit.controller.dtos.workoutroutine.create.WorkoutRoutineRequest;
import com.notifit.entity.member.Member;
import com.notifit.entity.workout.Workout;
import com.notifit.entity.workout.enums.BodyPart;
import com.notifit.entity.workoutset.enums.Unit;
import com.notifit.repository.MemberRepository;
import com.notifit.repository.WorkoutRepository;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Transactional
@SpringBootTest
public class WorkoutRoutineServiceTest {

    @Autowired
    private WorkoutRoutineService workoutRoutineService;
    @Autowired
    private WorkoutRepository workoutRepository;
    @Autowired
    private MemberRepository memberRepository;

    @Test
    @DisplayName("운동루틴 생성 요청 정보와 회원 ID 를 통해 운동 루틴을 저장한다.")
    void createWorkoutRoutineTest() {
        // given
        String username = "username";
        Member member = createMember(username);
        String workoutName = "benchPress";
        Workout workout = createWorkout(workoutName);

        List<WorkoutSetRequest> workoutSetRequests = new ArrayList<>();
        WorkoutSetRequest workoutSetRequest = WorkoutSetRequest.builder()
                .setNumber(1)
                .reps(10)
                .weight(100)
                .unit(Unit.KG)
                .memo("memo")
                .build();
        workoutSetRequests.add(workoutSetRequest);

        List<WorkoutRequest> workoutRequests = new ArrayList<>();
        WorkoutRequest workoutRequest = WorkoutRequest.builder()
                .workoutId(workout.getId())
                .workoutSetRequests(workoutSetRequests)
                .build();
        workoutRequests.add(workoutRequest);

        WorkoutRoutineRequest workoutRoutineRequest = WorkoutRoutineRequest.builder()
                .routineName("routineName")
                .workoutRequests(workoutRequests)
                .build();

        // when
        workoutRoutineService.createWorkoutRoutine(workoutRoutineRequest, username);

        //todo
        // then

    }

    private Member createMember(String username) {
        Member member = Member.of(username,
                "pssword",
                "name",
                "phoneNumber",
                true,
                true);
        return memberRepository.save(member);
    }

    private Workout createWorkout(String workoutName) {
        Workout workout = Workout.of(BodyPart.CHEST, workoutName);
        return workoutRepository.save(workout);
    }
}
