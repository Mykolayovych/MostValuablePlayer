package com.example.skaitest.models;

import java.util.List;
import java.util.Map;

/**
 * Цей інтерфейс описує поведінку матчу.
 */
public interface Match {
    /**
     * Цей метод отримує вміст файлу та відображає інформацію в об’єкті з відповідними атрибутами.
     * @param fileContent the {@Link List<String>} інформація про гравців на матч.
     */
    void processPlayerStats(List<String> fileContent);

    /**
     Цей метод перевіряє наявність дублікатів гравців в одній грі.
     */
    default String CheckPlayer(Map<String, String> temp, String key, String value){

        if(temp.size() != 0){
            for (int i = 0; i <temp.size() ; i++) {
                if(temp.containsKey(key)) {
                    return "Duplicate name Player: " + key;
                }
                else if (temp.containsValue(value)) {
                    return "Duplicate nickname Player: " +value;
                }
            }
        }
        temp.put(key,value);

        return null;
    }

    /**
     Цей метод повертає назву команду-переможця.
     */
    String winnerTeam();

    /**
     * Повертає колекцію map із псевдонімами як ключами та балами як значення.
     * @return в {@Link Map<String, Integer>}
     */
    Map<String, Integer> getPlayerStats();
}
