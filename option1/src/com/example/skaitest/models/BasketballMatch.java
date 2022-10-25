package com.example.skaitest.models;

import com.example.skaitest.exception.MostValuablePlayerException;
import java.util.*;
import static com.example.skaitest.enums.Const.*;
import static java.util.stream.Collectors.groupingBy;
import static java.util.stream.Collectors.summingInt;

public class BasketballMatch implements Match{

    Set<BasketballPlayer> playerSet;

    public BasketballMatch(){
        this.playerSet = new HashSet<>();
    }
    /*
      Цей метод отримує список рядків і відображає інформацію про гравця, який грає у баскетбол.
      Він зберігає гравця в наборі playerSet (змінна екземпляра).
      @param fileContent the {@Link List<String>} інформація про гравців на матч.
     *                    Перший рядок містить вид спорту, і він ігнорується.
     */
    @Override
    public void processPlayerStats(List<String> fileContent) {
        String name = null, nickname= null, teamName= null, number= null, scoredPoints= null, rebounds= null, assists= null;
        BasketballPlayer bPlayer;
        Map<String, String> temp = new HashMap<>();

        for (int i=1; i<fileContent.size(); i++) {
            StringTokenizer tokenizer = new StringTokenizer(fileContent.get(i), DELIM);

            while (tokenizer.hasMoreElements()) {
                try {
                    name = tokenizer.nextToken();
                    nickname = tokenizer.nextToken();
                    number = tokenizer.nextToken();
                    teamName = tokenizer.nextToken();
                    scoredPoints = tokenizer.nextToken();
                    rebounds = tokenizer.nextToken();
                    assists = tokenizer.nextToken();
                }
                catch (RuntimeException e){
                    System.out.printf("Wrong file input format data %s%n",fileContent.get(i));
                    System.exit(1);
                }

                    if(CheckPlayer(temp, name, nickname) != null){
                        System.out.println(CheckPlayer(temp, name, nickname));
                        System.exit(1);
                    }
                    temp.put(name,nickname);
                assert number != null;
                assert scoredPoints != null;
                bPlayer = new BasketballPlayer(name, nickname, Integer.parseInt(number), teamName, Integer.parseInt(scoredPoints), Integer.parseInt(rebounds), Integer.parseInt(assists));
                    playerSet.add(bPlayer);
            }
        }
    }

    /*
      Ця функція обчислює команду-переможця матчу. Спочатку розділіляє playerSet на teamName, обчислюючи
      загальний бал команди та повертає назву команди з найвищим балом.
     */
    @Override
    public String winnerTeam() {
        Map<String, Integer> pointsPerTeam = playerSet.stream().
                collect(groupingBy(BasketballPlayer::getTeamName, summingInt(BasketballPlayer::getScoredPoints)));
        List<String> teamsNames = pointsPerTeam.keySet().stream().toList();

        if (pointsPerTeam.get(teamsNames.get(0)) > pointsPerTeam.get(teamsNames.get(1))) {
            return teamsNames.get(0);
        } else {
            return teamsNames.get(1);
        }
    }

    /*
      Ця функція обчислює очки для кожного гравця в матчі та повертає map з псевдонімом як ключовим і
      загальною сумою балів як значення.
     */
    @Override
    public Map<String, Integer> getPlayerStats() {
        Map<String, Integer> result = new HashMap<>();
        int playerPoints;
        try {
            for (BasketballPlayer player : playerSet) {
                playerPoints = player.calculateMatchPoints();
                if (player.getTeamName().equals(this.winnerTeam())) {
                    result.put(player.getNickname(), playerPoints+WIN_POINTS);
                } else {
                    result.put(player.getNickname(), playerPoints);
                }
            }
        } catch (MostValuablePlayerException e) {
            e.printStackTrace();
        }

        return result;
    }
}
