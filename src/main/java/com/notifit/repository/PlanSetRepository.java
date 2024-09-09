package com.notifit.repository;

import com.notifit.entity.planset.PlanSet;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PlanSetRepository extends JpaRepository<PlanSet, Long> {
}
