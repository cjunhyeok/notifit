package com.notifit.repository;

import com.notifit.entity.member.Member;
import com.notifit.entity.workoutroutine.WorkoutRoutine;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;

import static org.assertj.core.api.Assertions.*;

@Transactional
@SpringBootTest
class WorkoutRoutineRepositoryTest {

    @Autowired
    private WorkoutRoutineRepository workoutRoutineRepository;
    @Autowired
    private MemberRepository memberRepository;

    @Test
    @DisplayName("운동 루틴 엔티티를 저장한다.")
    void saveTest() {
        // given
        String username = "username";
        Member member = createMember(username);
        Member savedMember = memberRepository.save(member);

        String routineName = "chestDay";
        LocalDateTime lastWorkoutTime = LocalDateTime.now();
        WorkoutRoutine workoutRoutine = WorkoutRoutine.of(routineName, lastWorkoutTime, savedMember);

        // when
        WorkoutRoutine savedWorkoutRoutine = workoutRoutineRepository.save(workoutRoutine);

        // then
        assertThat(savedWorkoutRoutine.getId()).isNotNull();
        assertThat(savedWorkoutRoutine.getRoutineName()).isEqualTo(routineName);
        assertThat(savedWorkoutRoutine.getLastWorkoutTime()).isEqualTo(lastWorkoutTime);
        assertThat(savedWorkoutRoutine.getMember()).isEqualTo(savedMember);
    }

    private Member createMember(String username) {
        return Member.of(
                username,
                "password",
                "name",
                "phoneNumber",
                true,
                true);
    }
}