package com.example.skaitest.serviceImpl.impl;

import com.example.skaitest.gameService.model.BasketballPlayer;
import com.example.skaitest.gameService.model.Match;
import com.example.skaitest.serviceImpl.PlayerService;
import org.springframework.stereotype.Service;
import java.util.regex.Matcher;
import static com.example.skaitest.gameService.enums.Const.*;

@Service("BasketballPlayerServiceImpl")
public class BasketballPlayerServiceImpl implements PlayerService<BasketballPlayer> {

    @Override
    public BasketballPlayer parseLineToPlayer(String line) {
        Matcher mtch = basketballPlayerPattern.matcher(line);

        if (!mtch.matches()){
            throw new IllegalStateException(String.format("Line %s is not valid", line));
        }
        String[] split = line.split(DELIM);
        BasketballPlayer player = new BasketballPlayer();
        player.setName(split[0]);
        player.setNickname(split[1]);
        player.setNumber(Integer.parseInt(split[2]));
        player.setTeamName(split[3]);
        player.setScoredPoints(Integer.parseInt(split[4]));
        player.setRebounds(Integer.parseInt(split[5]));
        player.setAssists(Integer.parseInt(split[6]));
        player.setPointsForTeamVictory(player.getScoredPoints());
        ratingPointsCount(player);
        return player;
    }

    @Override
    public void ratingPointsCount(BasketballPlayer basketballPlayer) {
        int ratingPoints = basketballPlayer.getScoredPoints() * SCORED_POINT + basketballPlayer.getRebounds()
                * REBOUNDS + basketballPlayer.getAssists() * ASSIST;
        basketballPlayer.setRatingPoints(ratingPoints);
    }

    @Override
    public void addRatingPointsForWinners(Match match) {
        match.getPlayers().stream().filter(player -> player.getTeamName().equals(match.getWinner().getName()))
                .forEach(player -> player.addRatingPoints(WIN_POINTS));
    }
}
