package com.notifit.entity.workoutroutine;

import com.notifit.entity.member.Member;
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

    public WorkoutRoutine(String routineName, LocalDateTime lastWorkoutTime, Member member) {
        this.routineName = routineName;
        this.lastWorkoutTime = lastWorkoutTime;
        this.member = member;
    }

    public static WorkoutRoutine of (String routineName, LocalDateTime lastWorkoutTime, Member member) {
        return new WorkoutRoutine(
                routineName,
                lastWorkoutTime,
                member
        );
    }
}
