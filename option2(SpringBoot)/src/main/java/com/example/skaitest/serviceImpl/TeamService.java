package com.example.skaitest.serviceImpl;

import com.example.skaitest.gameService.model.Team;
import java.util.ArrayList;
import java.util.Optional;

public interface TeamService {

    Optional<Team> getTeamFromListByName(ArrayList<Team> teams, String name);
}
