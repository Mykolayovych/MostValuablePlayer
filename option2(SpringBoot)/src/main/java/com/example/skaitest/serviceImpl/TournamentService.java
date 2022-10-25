package com.example.skaitest.serviceImpl;

import com.example.skaitest.gameService.model.Player;
import com.example.skaitest.gameService.model.Tournament;
import java.util.Optional;

public interface TournamentService {

    void tournamentProcessingFromFiles(String path);

    void setMvp(Tournament tournament);

    void setPlayerListForTournament(Tournament tournament);

    Optional<Player> findPlayerByNicknameInTournament(Tournament tournament, String nickname);
}
