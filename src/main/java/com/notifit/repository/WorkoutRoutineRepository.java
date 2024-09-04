package com.notifit.repository;

import com.notifit.entity.workoutroutine.WorkoutRoutine;
import org.springframework.data.jpa.repository.JpaRepository;

public interface WorkoutRoutineRepository extends JpaRepository<WorkoutRoutine, Long> {
}
