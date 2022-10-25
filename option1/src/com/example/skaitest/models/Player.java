package com.example.skaitest.models;

import com.example.skaitest.exception.MostValuablePlayerException;
import java.util.Objects;

/**
 * Цей абстрактий клас описує поведінку гравця.
 */
public abstract class Player {

    private String name;
    private String nickname;
    private int number;
    private String teamName;

    public Player(String name, String nickname, int number, String teamName) {
        this.name = name;
        this.nickname = nickname;
        this.number = number;
        this.teamName = teamName;
    }

    public abstract int calculateMatchPoints() throws MostValuablePlayerException;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    public int getNumber() {
        return number;
    }

    public void setNumber(int number) {
        this.number = number;
    }

    public String getTeamName() {
        return teamName;
    }

    public void setTeamName(String teamName) {
        this.teamName = teamName;
    }

    @Override
    public boolean equals(Object o) {
        return (o instanceof Player) && (this.nickname.equals(((Player) o).getNickname()));
    }

    @Override
    public int hashCode() {
        return Objects.hash(nickname);
    }

    @Override
    public String toString(){
        return "Player name: " + this.name + " Nickname: "+this.nickname+ " Number: "+this.number + " Team Name" + this.teamName;
    }
}
