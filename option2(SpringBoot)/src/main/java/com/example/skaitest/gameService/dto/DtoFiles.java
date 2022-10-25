package com.example.skaitest.gameService.dto;

import java.util.ArrayList;

public class DtoFiles {

    private String gameName;
    private ArrayList<String> lines = new ArrayList<>();

    public String getGameName() {
        return gameName;
    }

    public void setGameName(String gameName) {
        this.gameName = gameName;
    }

    public ArrayList<String> getLines() {
        return lines;
    }

    public void setLines(ArrayList<String> lines) {
        this.lines = lines;
    }

}
