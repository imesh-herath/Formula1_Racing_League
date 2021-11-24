package com.w1809809;

import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;
import java.awt.*;
import java.text.SimpleDateFormat;
import java.util.Collections;
import java.util.List;

public class F1ChampionshipGUI {

    static String[] driversHeader = new String[]{"Driver Number", "Name", "Location", "Team", "1st Places", "2nd Places", "3rd Places", "Fastest Laps", "Hat-Tricks", "Total Points", "Participated Races"};
    static String[] racingHeader = new String[]{"Date", "1st Place", "2nd Place", "3rd Place", "4th Place", "5th Place", "6th Place", "7th Place", "8th Place", "9th Place", "10th Place"};
    static Formula1ChampionshipManager formula1ChampionshipManager = new Formula1ChampionshipManager();

    public F1ChampionshipGUI() {
        // Main_Frame
        JFrame frame = new JFrame();

        //Header_Field
        JLabel label = new JLabel("Welcome to the 2021 FIA Formula One Championship!", SwingConstants.CENTER);
        label.setFont(new Font("Arial", Font.BOLD, 25));
        label.setBounds(100, 20, 700,22);

        // Search_Fields
        JPanel searchPanel = new JPanel();
        searchPanel.setBounds(250, 80, 300, 50);
        searchPanel.setLayout(null);

        JTextField searchingText = new JTextField();
        searchingText.setBounds(50,0,150,30);

        JButton searchButton = new JButton("Search");
        searchButton.setBorder(new RoundedBoarderInButtons(10));
        searchButton.setBounds(210,0,90,30);

        searchPanel.add(searchingText);
        searchPanel.add(searchButton);

        //Table_Configuration
        JScrollPane scrollPane = new JScrollPane();  // Child_Pane
        scrollPane.setLayout(new ScrollPaneLayout());
        scrollPane.setBounds(15,150,800,400);

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
        panelButtons.setBounds(50,585,800,30);
        panelButtons.setLayout(null);

        JButton sortByPoint = new JButton("Sort By Points");
        sortByPoint.setBorder(new RoundedBoarderInButtons(10));
        sortByPoint.setBounds(0,0,120,30);

        JButton sortByWinRaces = new JButton("Sort By Race Wins");
        sortByWinRaces.setBorder(new RoundedBoarderInButtons(10));
        sortByWinRaces.setBounds(150,0,140,30);

        JButton displayTheRaces = new JButton("Display The Races");
        displayTheRaces.setBorder(new RoundedBoarderInButtons(10));
        displayTheRaces.setBounds(300,0,140,30);

        JButton generateRaces = new JButton("Generate Races");
        generateRaces.setBorder(new RoundedBoarderInButtons(10));
        generateRaces.setBounds(450,0,120,30);

        JButton tableReset = new JButton("Reset");
        tableReset.setBorder(new RoundedBoarderInButtons(10));
        tableReset.setBounds(600,0,120,30);

        panelButtons.add(sortByPoint);
        panelButtons.add(sortByWinRaces);
        panelButtons.add(displayTheRaces);
        panelButtons.add(generateRaces);
        panelButtons.add(tableReset);

        // Add_ActionListeners_to_Button
        searchButton.addActionListener(e -> {
            List<Race> filterList = formula1ChampionshipManager.filterByDriverNumber(searchingText.getText());
            String[][] sortByPointsData = convertRaceDetailsTo2dArray(filterList);
            tableModel.setDataVector(sortByPointsData, dataColumn);
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
            tableModel.setDataVector(sortByPointsData, dataColumn);
        });

//        generateRaces.addActionListener(e -> {
//            try {
//                formula1ChampionshipManager.generateRandomRace(); // Generating_the_random_races
//            } catch (ParseException exception){
//                exception.printStackTrace();
//            }
//
//            String[][] sortByPointsData = convertDriverDetailsTo2dArray(Formula1ChampionshipManager.listOfRaces);
//            String[] columnHeader = racingHeader;
//            tableModel.setDataVector(sortByPointsData, columnHeader);
//        });

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
        frame.setAlwaysOnTop(true);
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        frame.setLayout(null); // To_Use_SetBound_Methods
        frame.setVisible(true);  // Frame_Properties
        frame.setSize(850,700);
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

//        Collections.sort(dataOfTheRace, new Comparator<Race>() {
//            @Override
//            public int compare(Race o1, Race o2) {
//                return o1.getDate().compareTo(o2.getDate());
//            }
//        });

        String[][] formattedArray = new String[dataOfTheRace.size()][11];

        for (int i = 0; i < dataOfTheRace.size(); i++) {
            Race race = dataOfTheRace.get(i);
            String[] temp = new String[11];

            // Format Date
            String pattern = "yyyy-MM-dd";
            SimpleDateFormat simpleDateFormat = new SimpleDateFormat(pattern);
            temp[0] = simpleDateFormat.format(race.getDate());

            race.getDriverPlaceMap().forEach((key, value) -> {
                temp[value] = key.getNameOfTheDriver();
            });
            formattedArray[i] = temp;
        }
        return formattedArray;
    }

    private static class RoundedBoarderInButtons implements Border {

        private int radius;

        RoundedBoarderInButtons(int radius) {
            this.radius = radius;
        }

        public Insets getBorderInsets(Component c) {
            return new Insets(this.radius + 1, this.radius + 1, this.radius + 2, this.radius);
        }

        public boolean isBorderOpaque() {
            return true;
        }

        public void paintBorder(Component c, Graphics g, int x, int y, int width, int height) {
            g.drawRoundRect(x, y, width - 1, height - 1, radius, radius);
        }
    }
}
