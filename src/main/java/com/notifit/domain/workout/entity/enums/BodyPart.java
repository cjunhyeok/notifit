package com.notifit.domain.workout.entity.enums;

public enum BodyPart {

    CHEST("가슴"),
    BACK("등"),
    SHOULDER("어께"),
    LEG("하체"),
    BICEPS("이두"),
    TRICEPS("삼두"),
    ETC("기타"),
    ;

    private final String description;

    BodyPart(String description) {
        this.description = description;
    }
}
