package com.example.skaitest.serviceImpl;

import com.example.skaitest.gameService.model.Match;
import com.example.skaitest.gameService.model.Player;

public interface PlayerService<T extends Player>{

    T parseLineToPlayer(String line);

    void ratingPointsCount(T player);

    void addRatingPointsForWinners(Match match);
}
