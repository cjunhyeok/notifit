package com.notifit.entity.workoutplan;

import com.notifit.converter.DurationConverter;
import com.notifit.entity.member.Member;
import com.notifit.entity.workoutset.WorkoutSet;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.Duration;
import java.time.LocalDate;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class WorkoutPlan {

    @Id
    @GeneratedValue
    @Column(name = "workout_plan_id")
    private Long id;

    private LocalDate scheduleDate;
    private boolean isCompleted;
    @Convert(converter = DurationConverter.class)
    private Duration elapsedTime;

    @JoinColumn(name = "member_id")
    @ManyToOne(fetch = FetchType.LAZY)
    private Member member;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "workout_set_id")
    private WorkoutSet workoutSet;

    private WorkoutPlan(LocalDate scheduleDate, boolean isCompleted, Duration elapsedTime, Member member, WorkoutSet workoutSet) {
        this.scheduleDate = scheduleDate;
        this.isCompleted = isCompleted;
        this.elapsedTime = elapsedTime;
        this.member = member;
        this.workoutSet = workoutSet;
    }

    public static WorkoutPlan of(LocalDate scheduleDate, boolean isCompleted, Duration elapsedTime, Member member, WorkoutSet workoutSet) {
        return new WorkoutPlan(
                scheduleDate,
                isCompleted,
                elapsedTime,
                member,
                workoutSet
        );
    }
}
