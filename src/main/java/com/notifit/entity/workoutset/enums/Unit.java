package com.notifit.entity.workoutset.enums;

public enum Unit {

    KG("킬로그램"),
    LB("파운드"),
    ;

    private final String description;

    Unit(String description) {
        this.description = description;
    }
}
