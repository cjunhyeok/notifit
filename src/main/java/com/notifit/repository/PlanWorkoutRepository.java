package com.notifit.repository;

import com.notifit.entity.planworkout.PlanWorkout;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PlanWorkoutRepository extends JpaRepository<PlanWorkout, Long> {
}
