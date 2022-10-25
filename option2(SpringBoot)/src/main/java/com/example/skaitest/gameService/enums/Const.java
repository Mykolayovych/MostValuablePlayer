package com.example.skaitest.gameService.enums;

import java.util.regex.Pattern;

public class Const {

    public static final int WIN_POINTS = 10;
    public static final String DELIM = ";";
    public static final Pattern basketballPlayerPattern = Pattern.compile("[^;]+;[^;]+;\\d+;[^;]+;\\d+;\\d+;\\d+");
    public static final Pattern handballPlayerPattern = Pattern.compile("[^;]+;[^;]+;\\d+;[^;]+;\\d+;\\d+");
    public static final int GOAL_MADE = 2;
    public static final int GOAL_RECEIVED = -1;
    public static final int SCORED_POINT = 2;
    public static final int REBOUNDS = 1;
    public static final int ASSIST = 1;
}
