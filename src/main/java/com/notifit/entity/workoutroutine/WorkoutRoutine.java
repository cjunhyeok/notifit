package com.notifit.entity.workoutroutine;

import com.notifit.entity.member.Member;
import com.notifit.entity.workoutset.WorkoutSet;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class WorkoutRoutine {

    @Id
    @GeneratedValue
    @Column(name = "workout_routine_id")
    private Long id;

    private String routineName;
    private LocalDateTime lastWorkoutTime;

    @JoinColumn(name = "member_id")
    @ManyToOne(fetch = FetchType.LAZY)
    private Member member;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "workout_set_id")
    private WorkoutSet workoutSet;

    public WorkoutRoutine(String routineName, LocalDateTime lastWorkoutTime, Member member, WorkoutSet workoutSet) {
        this.routineName = routineName;
        this.lastWorkoutTime = lastWorkoutTime;
        this.member = member;
        this.workoutSet = workoutSet;
    }

    public static WorkoutRoutine of (String routineName, LocalDateTime lastWorkoutTime, Member member, WorkoutSet workoutSet) {
        return new WorkoutRoutine(
                routineName,
                lastWorkoutTime,
                member,
                workoutSet
        );
    }
}
