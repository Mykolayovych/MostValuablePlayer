package com.example.skaitest.models;

import com.example.skaitest.exception.MostValuablePlayerException;
import java.util.*;
import static com.example.skaitest.enums.Const.*;
import static java.util.stream.Collectors.groupingBy;
import static java.util.stream.Collectors.summingInt;

public class HandballMatch implements Match {

    Set<HandballPlayer> playerSet;

    public HandballMatch() {
        this.playerSet = new HashSet<>();
    }

    @Override
    public void processPlayerStats(List<String> fileContent) {
        String name = null, nickname = null, teamName = null, number = null, goalsMade = null, goalsReceived = null;
        HandballPlayer hPlayer;
        Map<String, String> temp = new HashMap<>();
        for (int i=1; i<fileContent.size(); i++) {
            StringTokenizer tokenizer = new StringTokenizer(fileContent.get(i), DELIM);
            while (tokenizer.hasMoreElements()) {
                try {
                    name = tokenizer.nextToken();
                    nickname = tokenizer.nextToken();
                    number = tokenizer.nextToken();
                    teamName = tokenizer.nextToken();
                    goalsMade = tokenizer.nextToken();
                    goalsReceived = tokenizer.nextToken();
                }
                catch (RuntimeException e){
                    System.out.printf("Wrong file input format data %s%n", fileContent.get(i));
                    System.exit(1);
                }

                if(CheckPlayer(temp, name, nickname) != null){
                    System.out.println(CheckPlayer(temp, name, nickname));
                    System.exit(1);
                }
                assert goalsMade != null;
                assert goalsReceived != null;
                hPlayer = new HandballPlayer(name, nickname, Integer.parseInt(number), teamName,
                        Integer.parseInt(goalsMade), Integer.parseInt(goalsReceived));
                playerSet.add(hPlayer);
            }
        }
    }

    @Override
    public String winnerTeam() {
        Map<String, Integer> pointsPerTeam = playerSet.stream()
                .collect(groupingBy(HandballPlayer::getTeamName, summingInt(HandballPlayer::getGoalsMade)));
        List<String> teamsNames = pointsPerTeam.keySet().stream().toList();

        if (pointsPerTeam.get(teamsNames.get(0)) > pointsPerTeam.get(teamsNames.get(1))) {
            return teamsNames.get(0);
        } else {
            return teamsNames.get(1);
        }
    }

    @Override
    public Map<String, Integer> getPlayerStats() {
        Map<String, Integer> result = new HashMap<>();
        int playerPoints;
        try {
            for (HandballPlayer player : playerSet) {
                playerPoints = player.calculateMatchPoints();
                if (player.getTeamName().equals(this.winnerTeam())) {
                    result.put(player.getNickname(), playerPoints+WIN_POINTS);
                } else {
                    result.put(player.getNickname(), playerPoints);
                }
            }
        } catch (MostValuablePlayerException e) {
            e.printStackTrace();
        }

        return result;
    }
}
