package com.notifit.repository;

import com.notifit.entity.routineworkout.RoutineWorkout;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RoutineWorkoutRepository extends JpaRepository<RoutineWorkout, Long> {
}
