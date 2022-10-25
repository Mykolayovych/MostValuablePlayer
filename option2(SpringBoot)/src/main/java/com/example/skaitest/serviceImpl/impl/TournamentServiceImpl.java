package com.example.skaitest.serviceImpl.impl;

import com.example.skaitest.gameService.model.Match;
import com.example.skaitest.gameService.model.Player;
import com.example.skaitest.gameService.model.Tournament;
import com.example.skaitest.gameService.dto.DtoFiles;
import com.example.skaitest.serviceImpl.FileService;
import com.example.skaitest.serviceImpl.MatchService;
import com.example.skaitest.serviceImpl.TournamentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;

@Service
public class TournamentServiceImpl implements TournamentService {

    private final FileService fileService;
    private final MatchService matchService;

    @Autowired
    public TournamentServiceImpl(FileService fileService, MatchService matchService) {
        this.fileService = fileService;
        this.matchService = matchService;
    }

    @Override
    public void tournamentProcessingFromFiles(String path) {
        List<DtoFiles> fileDTOs = fileService.getFileDTOList(path);
        Tournament tournament = new Tournament();

        for (DtoFiles fileDTO : fileDTOs) {
            Match matchByFileDTO = matchService.parseFileDtoToMatch(fileDTO);
            tournament.getMatches().add(matchByFileDTO);
        }
        setPlayerListForTournament(tournament);
        setMvp(tournament);
        System.out.println(tournament.getMvp());
    }

    @Override
    public void setMvp(Tournament tournament) {
        tournament.setMvp(tournament.getPlayers().stream().max(Comparator.comparingInt(Player::getRatingPoints)).get());
    }

    @Override
    public void setPlayerListForTournament(Tournament tournament) {

        for (Match match : tournament.getMatches()) {
            for (Player player : match.getPlayers()) {
                Optional<Player> playerByNickname = findPlayerByNicknameInTournament(tournament, player.getNickname());

                if (playerByNickname.isPresent()) {
                    playerByNickname.get().addRatingPoints(player.getRatingPoints());
                } else {
                    Player newPlayer = new Player();
                    newPlayer.setNickname(player.getNickname());
                    newPlayer.setRatingPoints(player.getRatingPoints());
                    tournament.getPlayers().add(newPlayer);
                }
            }
        }
    }

    @Override
    public Optional<Player> findPlayerByNicknameInTournament(Tournament tournament, String nickname) {
        return Optional.of(tournament.getPlayers().stream().filter(player -> player.getNickname().equals(nickname))
                .findFirst()).orElse(Optional.empty());
    }

}
