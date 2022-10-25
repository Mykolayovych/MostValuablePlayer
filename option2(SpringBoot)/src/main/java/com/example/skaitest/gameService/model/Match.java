package com.example.skaitest.gameService.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import java.util.ArrayList;
import java.util.HashSet;

@Getter
@Setter
@NoArgsConstructor
public class Match {

    private ArrayList<Team> teams = new ArrayList<>();
    private HashSet<Player> players = new HashSet<>();
    private Team winner;

    @Override
    public String toString() {
        return "Match{" +
                "teams=" + teams +
                ", players=" + players +
                ", winner=" + winner +
                '}';
    }
}
