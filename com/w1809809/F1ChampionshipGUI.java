package com.w1809809;

import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;
import java.awt.*;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.List;

public class F1ChampionshipGUI {

    static String[] driversHeader = new String[]{"Driver Number", "Name", "Location", "Team", "1st Places", "2nd Places", "3rd Places", "Fastest Laps", "Hat-Tricks", "Total Points", "Races"};
    static String[] racingHeader = new String[]{"Date", "1st Place", "2nd Place", "3rd Place", "4th Place", "5th Place", "6th Place", "7th Place", "8th Place", "9th Place", "10th Place"};
    static Formula1ChampionshipManager formula1ChampionshipManager = new Formula1ChampionshipManager();

    public F1ChampionshipGUI() {
        // Main_Frame
        JFrame frame = new JFrame();

        //Header_Field
        JLabel label = new JLabel("Welcome to the 2021 FIA Formula One Championship!", SwingConstants.CENTER);
        label.setFont(new Font("Arial", Font.BOLD, 25));
        label.setBounds(300, 40, 700,22);

        // Search_Fields
        JPanel searchPanel = new JPanel();
        searchPanel.setBounds(50, 130, 300, 50);
        searchPanel.setLayout(null);

        JTextField searchingText = new JTextField();
        searchingText.setBounds(50,0,150,30);

        JButton searchButton = new JButton("Search");
        searchButton.setBounds(210,0,90,30);

        searchPanel.add(searchingText);
        searchPanel.add(searchButton);

        //Table_Configuration
        JScrollPane scrollPane = new JScrollPane();  // Child_Pane
        scrollPane.setLayout(new ScrollPaneLayout());
        scrollPane.setBounds(15,200,1250,300);

        // Table_Data
        String[][] dataRow = convertDriverDetailsTo2dArray(Formula1ChampionshipManager.listOfTheFormula1driver, false, false);
        String[] dataColumn = driversHeader;
        DefaultTableModel tableModel = new DefaultTableModel(dataRow, dataColumn);

        JTable table = new JTable(tableModel);
        table.setRowHeight(25);
        table.setCellSelectionEnabled(true);
        table.setFont(new Font("Arial", Font.ITALIC, 14));
        table.setAutoCreateRowSorter(true); //Enable_in-build_Sorter

        JTableHeader tableHeader = table.getTableHeader();
        tableHeader.setFont(new Font("Arial", Font.BOLD, 14));

        scrollPane.setViewportView(table);

        //Button_Field
        JPanel panelButtons = new JPanel();
        panelButtons.setBounds(400,130,800,30);
        panelButtons.setLayout(null);

        JButton sortByPoint = new JButton("Sort By Points");
        sortByPoint.setBounds(0,0,140,30);

        JButton sortByWinRaces = new JButton("Sort By Race Wins");
        sortByWinRaces.setBounds(150,0,140,30);

        JButton displayTheRaces = new JButton("Display The Races");
        displayTheRaces.setBounds(300,0,140,30);

        JButton generateRaces = new JButton("Generate Races");
        generateRaces.setBounds(450,0,140,30);

        JButton tableReset = new JButton("Reset");
        tableReset.setBounds(600,0,140,30);

        panelButtons.add(sortByPoint);
        panelButtons.add(sortByWinRaces);
        panelButtons.add(displayTheRaces);
        panelButtons.add(generateRaces);
        panelButtons.add(tableReset);

        // Add_ActionListeners_to_Button
        searchButton.addActionListener(e -> {
            List<Race> filterList = formula1ChampionshipManager.filterByDriverNumber(searchingText.getText());
            String[][] filteredData = convertRaceDetailsTo2dArray(filterList);
            tableModel.setDataVector(filteredData, racingHeader);
        });

        sortByPoint.addActionListener(e -> {
            String[][] sortByPointsData = convertDriverDetailsTo2dArray(Formula1ChampionshipManager.listOfTheFormula1driver, true, false);
            tableModel.setDataVector(sortByPointsData, dataColumn);
        });

        sortByWinRaces.addActionListener(e -> {
            String[][] sortByPointsData = convertDriverDetailsTo2dArray(Formula1ChampionshipManager.listOfTheFormula1driver, false, true);
            tableModel.setDataVector(sortByPointsData, dataColumn);
        });

        displayTheRaces.addActionListener(e -> {
            String[][] sortByPointsData = convertRaceDetailsTo2dArray(Formula1ChampionshipManager.listOfRaces);
            String[] columnHeader = racingHeader;
            tableModel.setDataVector(sortByPointsData, columnHeader);
        });

        generateRaces.addActionListener(e -> {
            formula1ChampionshipManager.randomRaceGenerator();

            String[][] sortByPointData = convertRaceDetailsTo2dArray(Formula1ChampionshipManager.listOfRaces);
            String[] colHeader = racingHeader;
            tableModel.setDataVector(sortByPointData, colHeader);
        });

        tableReset.addActionListener(e -> {
            String[][] sortByPointsData = convertDriverDetailsTo2dArray(Formula1ChampionshipManager.listOfTheFormula1driver, false, false);
            tableModel.setDataVector(sortByPointsData, dataColumn);
        });

        // Add_child_to_parent_Components
        frame.add(label);
        frame.add(searchPanel);
        frame.add(scrollPane);
        frame.add(panelButtons);

        //Create_An_Icon
        ImageIcon imageIcon = new ImageIcon("f1.png");
        frame.setIconImage(imageIcon.getImage());

        // Properties
        frame.setTitle("Formula1 Championship League 2021");
        frame.setResizable(false);
//        frame.setAlwaysOnTop(true);
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        frame.setLayout(null); // To_Use_SetBound_Methods
        frame.setVisible(true);  // Frame_Properties
        frame.setSize(1290,700);
    }
    public static void main(String[] args) {
        // Retrieve Data from Files
        formula1ChampionshipManager.retrieveTheDriverFromFile(Formula1ChampionshipManager.fileNameOfTheDriver);
        formula1ChampionshipManager.retrieveTheRaceFromFile(Formula1ChampionshipManager.fileNameOfTheRace);

        new F1ChampionshipGUI();
    }

    // Only applicable for Formulary1Driver List
    private String[][] convertDriverDetailsTo2dArray(List<Formula1Driver> source, boolean isSortedByPoints, boolean isSortedByWins) {
        List<Formula1Driver> dataOfTheDriver = source;

        if (isSortedByPoints) {
            Collections.sort(dataOfTheDriver, new ComparatorPoints());
        }

        if (isSortedByWins) {
            Collections.sort(dataOfTheDriver, new ComparatorWins());
        }

        String[][] formatArray = new String[dataOfTheDriver.size()][9];

        for (int i = 0; i < dataOfTheDriver.size(); i++) {
            Formula1Driver formula1Driver = dataOfTheDriver.get(i);
            formatArray[i] = new String[]{
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
        return formatArray;
    }

    // Only applicable for Race List
    private String[][] convertRaceDetailsTo2dArray(List<Race> source) {
        List<Race> dataOfTheRace = source;
        String pattern = "yyyy-MM-dd";
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat(pattern);

        Collections.sort(dataOfTheRace, (o1, o2) -> {
            Date date1 = null;
            Date date2 = null;
            try {
                date1 = simpleDateFormat.parse(o1.getDateString());
                date2 = simpleDateFormat.parse(o2.getDateString());
            } catch (ParseException e) {
                e.printStackTrace();
            }
            return date1.compareTo(date2);
        });

        String[][] formattedArray = new String[dataOfTheRace.size()][11];
        for (int i = 0; i < dataOfTheRace.size(); i++) {
            Race race = dataOfTheRace.get(i);

            String[] temp = new String[10];
            temp[0] = race.getDateString();
            race.getDriverPlaceMap().forEach((key, value) -> {
                temp[value] = key.getNameOfTheDriver();
            });
            formattedArray[i] = temp;
        }
        return formattedArray;
    }
}
