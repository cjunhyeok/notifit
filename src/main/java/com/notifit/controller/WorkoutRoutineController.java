package com.notifit.controller;

import com.notifit.controller.dtos.Result;
import com.notifit.controller.dtos.workoutroutine.create.WorkoutRoutineRequest;
import com.notifit.service.WorkoutRoutineService;
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
public class WorkoutRoutineController {

    @Autowired
    private WorkoutRoutineService workoutRoutineService;

    @PostMapping("/api/workout-routine")
    public Result createWorkoutRoutine(@RequestBody WorkoutRoutineRequest request,
                                       @SessionAttribute(name = SessionConst.SESSION_ID) String loginUsername) {

        workoutRoutineService.createWorkoutRoutine(request, loginUsername);

        return new Result(null, "운동루틴 저장에 성공했습니다.");
    }
}
