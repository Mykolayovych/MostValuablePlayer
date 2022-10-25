package com.example.skaitest.serviceImpl.impl;

import com.example.skaitest.gameService.model.Team;
import com.example.skaitest.serviceImpl.TeamService;
import org.springframework.stereotype.Service;
import java.util.ArrayList;
import java.util.Optional;

@Service
public class TeamServiceImpl implements TeamService {

    @Override
    public Optional<Team> getTeamFromListByName(ArrayList<Team> teams, String name) {
        return Optional.of(teams.stream().filter(team -> team.getName().equals(name)).findFirst()).orElse(Optional.empty());
    }


}
