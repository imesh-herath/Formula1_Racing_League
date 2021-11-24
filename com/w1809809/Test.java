package com.w1809809;

import java.util.List;

public class Test {

    static Formula1ChampionshipManager formula1ChampionshipManager = new Formula1ChampionshipManager();

    public static void main(String[] args) {
        // Retrieve Data from Files
        formula1ChampionshipManager.retrieveTheDriverFromFile(Formula1ChampionshipManager.fileNameOfTheDriver);
        formula1ChampionshipManager.retrieveTheRaceFromFile(Formula1ChampionshipManager.fileNameOfTheRace);

        List<Race> testFormat = formula1ChampionshipManager.sortByDate();

        testFormat.forEach(item -> {
            System.out.println(item.getDate().getDay()+"/"+item.getDate().getMonth()+"/"+item.getDate().getYear());
        });
    }
}
