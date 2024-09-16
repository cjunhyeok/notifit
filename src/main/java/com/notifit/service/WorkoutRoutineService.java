package com.notifit.service;

import com.notifit.controller.dtos.workoutroutine.create.WorkoutRequest;
import com.notifit.controller.dtos.workoutroutine.create.WorkoutRoutineRequest;
import com.notifit.controller.dtos.workoutroutine.create.WorkoutSetRequest;
import com.notifit.entity.member.Member;
import com.notifit.entity.routineworkout.RoutineWorkout;
import com.notifit.entity.workout.Workout;
import com.notifit.entity.workoutroutine.WorkoutRoutine;
import com.notifit.entity.workoutset.WorkoutSet;
import com.notifit.exception.CustomException;
import com.notifit.exception.ErrorCode;
import com.notifit.repository.*;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class WorkoutRoutineService {

    private final WorkoutRoutineRepository workoutRoutineRepository;
    private final WorkoutSetRepository workoutSetRepository;
    private final WorkoutRepository workoutRepository;
    private final RoutineWorkoutRepository routineWorkoutRepository;
    private final MemberRepository memberRepository;

    @Transactional
    public void createWorkoutRoutine(WorkoutRoutineRequest request, String username) {

        // 회원 조회
        Member findMember = memberRepository.findByUsername(username).orElseThrow(
                () -> new CustomException(ErrorCode.MEMBER_NOT_FOUND)
        );

        // 운동루틴 엔티티 저장
        String routineName = request.getRoutineName();
        LocalDateTime lastWorkoutDate = null;
        WorkoutRoutine workoutRoutine = WorkoutRoutine.of(routineName, lastWorkoutDate, findMember);
        WorkoutRoutine savedWorkoutRoutine = workoutRoutineRepository.save(workoutRoutine);

        List<WorkoutRequest> workouts = request.getWorkoutRequests();
        for (WorkoutRequest workout : workouts) {
            // 운동 조회
            Long workoutId = workout.getWorkoutId();
            Workout findWorkout = workoutRepository.findById(workoutId).orElseThrow(
                    () -> new CustomException(ErrorCode.WORKOUT_NOT_FOUND)
            );

            // 운동루틴, 운동 다대다 엔티티 저장
            RoutineWorkout routineWorkout = RoutineWorkout.of(savedWorkoutRoutine, findWorkout);
            routineWorkoutRepository.save(routineWorkout);

            // 운동 세트 저장
            List<WorkoutSetRequest> workoutSets = workout.getWorkoutSetRequests();
            for (WorkoutSetRequest set : workoutSets) {
                WorkoutSet workoutSet = WorkoutSet.of(
                        set.getSetNumber(),
                        set.getReps(),
                        set.getWeight(),
                        set.getUnit(),
                        set.getMemo(),
                        findWorkout
                );
                workoutSetRepository.save(workoutSet);
            }
        }
    }
}
