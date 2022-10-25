package com.example.skaitest.models;

import com.example.skaitest.exception.MostValuablePlayerException;
import static com.example.skaitest.enums.Const.*;

public class HandballPlayer extends Player{

    private final int goalsMade;
    private final int goalsReceived;

    public HandballPlayer(String name, String nickname, int number, String teamName, int goalsMade, int goalsReceived) {
        super(name, nickname, number, teamName);
        this.goalsMade = goalsMade;
        this.goalsReceived = goalsReceived;
    }

    public int getGoalsMade() {
        return goalsMade;
    }

    @Override
    public int calculateMatchPoints() throws MostValuablePlayerException {
        return (this.goalsMade*GOAL_MADE + this.goalsReceived*GOAL_RECEIVED);
    }
}
