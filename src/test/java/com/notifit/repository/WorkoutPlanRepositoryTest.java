package com.notifit.repository;

import com.notifit.entity.member.Member;
import com.notifit.entity.workoutplan.WorkoutPlan;
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
class WorkoutPlanRepositoryTest {

    @Autowired
    private WorkoutPlanRepository workoutPlanRepository;
    @Autowired
    private MemberRepository memberRepository;

    @Test
    @DisplayName("운동계획 엔티티를 저장한다.")
    void saveTest() {
        // given
        LocalDate scheduleDate = LocalDate.now();
        boolean isComplete = false;
        Duration elapsedTime = Duration.ofSeconds(90);
        String username = "username";
        Member member = createMember(username);
        Member savedMember = memberRepository.save(member);
        WorkoutPlan workoutPlan = WorkoutPlan.of(scheduleDate, isComplete, elapsedTime, savedMember);

        // when
        WorkoutPlan savedWorkoutPlan = workoutPlanRepository.save(workoutPlan);

        // then
        assertThat(savedWorkoutPlan.getId()).isNotNull();
        assertThat(savedWorkoutPlan.getScheduleDate()).isEqualTo(scheduleDate);
        assertThat(savedWorkoutPlan.getElapsedTime()).isEqualTo(elapsedTime);
        assertThat(savedWorkoutPlan.getMember()).isEqualTo(member);
    }

    private Member createMember(String username) {
        return Member.of(username, "password", "name", "phoneNumber", true, true);
    }
}