package com.w1809809;

import javax.swing.*;

public class F1ChampionshipGUI {
    static Formula1ChampionshipManager formula1ChampionshipManager = new Formula1ChampionshipManager();

    public static void main(String[] args) {

        // Update_The_List_from_File
        formula1ChampionshipManager.retrieveTheDriverFromFile(Formula1ChampionshipManager.fileNameOfTheDriver);
        formula1ChampionshipManager.retrieveTheDriverFromFile(Formula1ChampionshipManager.fileNameOfTheRace);

        // Main_Frame
        JFrame frame = new JFrame();
        JPanel scrollPane = new JPanel();  // Child_Pane
        scrollPane.setBounds(30,60,800,700);

        // Table
        JTable table = new JTable(getAllDrivers(), new String[]{"Driver Number", "Name", "Location", "Team", "1st Places", "2nd Places", "3rd Places", "Fastest Laps", "Hat-Tricks", "Total Points", "Participated Races"});
        table.setRowHeight(20);
        table.setBounds(50,50,600,500);


        frame.setResizable(false);
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        frame.setTitle("Formula1 Championship League");
        scrollPane.add(table);  // Add_Child_Components_to_Parent
        frame.add(scrollPane);
        frame.setVisible(true);  // Frame_Properties
        frame.setSize(1050,500);

    }

    private static String[][] getAllDrivers() {
        String[][] sortedList2d = new String[Formula1ChampionshipManager.listOfTheFormula1driver.size()+1][11];
        for (int i = 0; i < Formula1ChampionshipManager.listOfTheFormula1driver.size(); i++) {
            Formula1Driver formula1Driver = Formula1ChampionshipManager.listOfTheFormula1driver.get(i);
            sortedList2d[i+1] = new String[]{
                    String.valueOf(formula1Driver.getDriverNumber()),
                    formula1Driver.getNameOfTheDriver(),
                    formula1Driver.getLocationOfTheDriver(),
                    formula1Driver.getTeamNameOfTheDriver(),
                    String.valueOf(formula1Driver.getNumberOfGoldMedals()),
                    String.valueOf(formula1Driver.getNumberOfSilverMedals()),
                    String.valueOf(formula1Driver.getNumberOfBronzeMedals()),
                    String.valueOf(formula1Driver.getNumberOfFastestLaps()),
                    String.valueOf(formula1Driver.getNumberOfHatTricks()),
                    String.valueOf(formula1Driver.getTotalPoints()),
                    String.valueOf(formula1Driver.getNumberOfParticipation())

            };
        }
        return sortedList2d;
    }
}
