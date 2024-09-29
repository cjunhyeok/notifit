package com.notifit.service;

import com.notifit.controller.dtos.workout.create.WorkoutRequest;
import com.notifit.controller.dtos.workout.create.WorkoutSetRequest;
import com.notifit.controller.dtos.workoutplan.create.WorkoutPlanRequest;
import com.notifit.entity.member.Member;
import com.notifit.entity.planworkout.PlanWorkout;
import com.notifit.entity.workout.Workout;
import com.notifit.entity.workoutplan.WorkoutPlan;
import com.notifit.entity.workoutset.WorkoutSet;
import com.notifit.exception.CustomException;
import com.notifit.exception.ErrorCode;
import com.notifit.repository.*;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.Duration;
import java.time.LocalDate;
import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class WorkoutPlanService {

    private final WorkoutPlanRepository workoutPlanRepository;
    private final WorkoutSetRepository workoutSetRepository;
    private final WorkoutRepository workoutRepository;
    private final PlanWorkoutRepository planWorkoutRepository;
    private final MemberRepository memberRepository;

    @Transactional
    public void createWorkoutPlan(WorkoutPlanRequest request, String username) {

        Member findMember = memberRepository.findByUsername(username).orElseThrow(
                () -> new CustomException(ErrorCode.MEMBER_NOT_FOUND)
        );

        LocalDate scheduleDate = request.getScheduleDate();
        boolean isCompleted = false;
        Duration initElapsedTime = Duration.ZERO;
        WorkoutPlan workoutPlan = WorkoutPlan.of(scheduleDate, isCompleted, initElapsedTime, findMember);
        WorkoutPlan savedWorkoutPlan = workoutPlanRepository.save(workoutPlan);

        List<WorkoutRequest> workouts = request.getWorkoutRequests();
        for (WorkoutRequest workout : workouts) {
            Long workoutId = workout.getWorkoutId();
            Workout findWorkout = workoutRepository.findById(workoutId).orElseThrow(
                    () -> new CustomException(ErrorCode.WORKOUT_NOT_FOUND)
            );

            PlanWorkout planWorkout = PlanWorkout.of(savedWorkoutPlan, findWorkout);
            planWorkoutRepository.save(planWorkout);

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
