package com.notifit.controller;

import com.notifit.controller.dtos.Result;
import com.notifit.controller.dtos.workoutplan.create.WorkoutPlanRequest;
import com.notifit.service.WorkoutPlanService;
import com.notifit.service.utils.SessionConst;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.SessionAttribute;

@Slf4j
@RestController
@RequiredArgsConstructor
public class WorkoutPlanController {

    @Autowired
    private final WorkoutPlanService workoutPlanService;

    @PostMapping("/api/workout-plan")
    public Result createWorkoutPlan(@RequestBody WorkoutPlanRequest request,
            @SessionAttribute(name = SessionConst.SESSION_ID) String loginUsername) {

        workoutPlanService.createWorkoutPlan(request, loginUsername);

        return new Result(null, "운동계획 저장에 성공했습니다.");
    }
}
