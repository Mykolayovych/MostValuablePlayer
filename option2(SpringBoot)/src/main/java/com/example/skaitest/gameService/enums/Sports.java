package com.example.skaitest.gameService.enums;

public enum Sports {

    BASKETBALL("BASKETBALL"), HANDBALL("HANDBALL");

    private final String name;

    Sports(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }
}
