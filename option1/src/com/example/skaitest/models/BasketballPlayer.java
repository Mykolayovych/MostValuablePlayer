package com.example.skaitest.models;

import com.example.skaitest.exception.MostValuablePlayerException;
import static com.example.skaitest.enums.Const.*;

public class BasketballPlayer extends Player {

    private final int scoredPoints;
    private final int rebounds;
    private final int assists;

    public BasketballPlayer(String name, String nickname, int number, String teamName, int scoredPoints, int rebounds, int assists) {
        super(name, nickname, number, teamName);
        this.scoredPoints = scoredPoints;
        this.rebounds = rebounds;
        this.assists = assists;
    }

    public int getScoredPoints() {
        return scoredPoints;
    }

    /*
     Ця функція повертає рейтингові бали в баскетбольному матчі в залежності від здобутків у матчі.
     */
    @Override
    public int calculateMatchPoints() throws MostValuablePlayerException{
        return (this.scoredPoints*SCORED_POINT + this.rebounds*REBOUNDS + this.assists*ASSIST);
    }
}
