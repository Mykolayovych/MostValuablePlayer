package com.example.skaitest.serviceImpl;

import com.example.skaitest.gameService.model.Match;
import com.example.skaitest.gameService.dto.DtoFiles;

public interface MatchService {

    Match parseFileDtoToMatch(DtoFiles fileDTO);

    Match getMatchFromDataDto(DtoFiles fileDTO, PlayerService playerService);

    void setTeamsForMatch(Match match);

    void setWinnerForMatch(Match match);
}
