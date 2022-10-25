package com.example.skaitest.serviceImpl.impl;

import com.example.skaitest.gameService.model.HandballPlayer;
import com.example.skaitest.gameService.model.Match;
import com.example.skaitest.serviceImpl.PlayerService;
import org.springframework.stereotype.Service;
import java.util.regex.Matcher;

import static com.example.skaitest.gameService.enums.Const.*;

@Service("HandballPlayerServiceImpl")
public class HandballPlayerServiceImpl implements PlayerService<HandballPlayer> {

    @Override
    public HandballPlayer parseLineToPlayer(String line) {
        String[] split = line.split(DELIM);
        Matcher mtch = handballPlayerPattern.matcher(line);

        if (!mtch.matches()){
            throw new IllegalStateException(String.format("Line %s is not valid", line));
        }
        HandballPlayer player = new HandballPlayer();
        player.setName(split[0]);
        player.setNickname(split[1]);
        player.setNumber(Integer.parseInt(split[2]));
        player.setTeamName(split[3]);
        player.setGoalsMade(Integer.parseInt(split[4]));
        player.setGoalsReceived(Integer.parseInt(split[5]));
        player.setPointsForTeamVictory(player.getGoalsMade());
        ratingPointsCount(player);

        return player;
    }

    @Override
    public void ratingPointsCount(HandballPlayer handballPlayer) {
        int ratingPoints = handballPlayer.getGoalsMade() * GOAL_MADE + handballPlayer.getGoalsReceived() * GOAL_RECEIVED;
        handballPlayer.setRatingPoints(ratingPoints);
    }

    @Override
    public void addRatingPointsForWinners(Match match) {
        match.getPlayers()
                .stream()
                .filter(player -> player.getTeamName().equals(match.getWinner().getName()))
                .forEach(player -> player.addRatingPoints(WIN_POINTS));
    }
}
