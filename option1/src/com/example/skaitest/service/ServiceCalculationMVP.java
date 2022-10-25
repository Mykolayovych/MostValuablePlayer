package com.example.skaitest.service;

import com.example.skaitest.enums.Sports;
import com.example.skaitest.models.BasketballMatch;
import com.example.skaitest.models.HandballMatch;
import com.example.skaitest.models.Match;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.*;

public class ServiceCalculationMVP {

    private final List<Match> matchList = new ArrayList<>();
    public String directory;

    public ServiceCalculationMVP(String directory) {
        this.directory = directory;
    }

    public String getMVP() {
        String mvpPlayer = null;
        try {
            //Обробка файлів
            Files.walk(Paths.get(directory)).filter(Files::isRegularFile).forEach(file -> processFile(file, matchList));
            //Розрахунок статистики для гравців
            Map<String, Integer> playerMap = new HashMap<>();

            for (Match match : matchList) {
                updatePlayersStats(match.getPlayerStats(), playerMap);
            }
            //Знайдення гравця із найвищим значенням у playerMap
            mvpPlayer = "Tournament MVP is " + getMVP(playerMap) + ", with " + playerMap.get(getMVP(playerMap)) + " points.";
        } catch (Exception e){
            System.out.println("Error occurred when reading the directory");
            System.exit(1);
        }

        return mvpPlayer;
    }

    /**
     * Цей метод обробляє файл, переданий як параметр, і включає всю інформацію про гравця в matchList.
     * @param file the {@Link Path} інформація про гравців для обробки.
     * @param matchList the {@Link List<Match>} список з інформацією про відповідність.
     */
    private void processFile(Path file, List<Match> matchList) {
        try {
            List<String> fileLines = Files.readAllLines(file);

            if (!fileLines.isEmpty() && Sports.BASKETBALL.toString().equals(fileLines.get(0))) {
                BasketballMatch bMatch = new BasketballMatch();
                bMatch.processPlayerStats(fileLines);
                matchList.add(bMatch);
            } else if (!fileLines.isEmpty() && Sports.HANDBALL.toString().equals((fileLines.get(0)))) {
                HandballMatch hMatch = new HandballMatch();
                hMatch.processPlayerStats(fileLines);
                matchList.add(hMatch);
            }
            else {
                System.out.println("Error name sport");
                System.exit(1);
            }
        } catch (IOException ex) {
            System.out.println("Error occurred when reading files");
            System.exit(1);
        }
    }

    /**
     * Цей метод оновлює статистику для гравців у playerMap за допомогою балів, отриманих за параметром у playerStats.
     * @param playerStats the {@Link Map<String, Integer>} з псевдонімом як ключовим і загальною кількістю балів за матч як значенням.
     * @param playerMap the {@Link Map<String, Integer>} із загальною статистикою всіх гравців.
     */
    private void updatePlayersStats(Map<String, Integer> playerStats, Map<String, Integer> playerMap) {

        // використання циклу for-each для повторення Map.entrySet()
        for(Map.Entry<String, Integer> entry : playerStats.entrySet()) {
            playerMap.merge(entry.getKey(), entry.getValue(), Integer::sum);
        }
    }

    /**
     * Ця функція повертає ключ із найвищим значенням у map.
     * @param map the {@Link Map<String, Integer> з псевдонімами гравців та оцінками як значеннями.
     * @return the {@Link String} з псевдонімом гравця з найвищим значенням.
     */
    private <String, Integer extends Comparable<Integer>> String getMVP(Map<String, Integer> map) {
        Optional<Map.Entry<String, Integer>> maxEntry = map.entrySet()
                .stream()
                .max(Map.Entry.comparingByValue());

        return maxEntry.get().getKey();
    }
}
