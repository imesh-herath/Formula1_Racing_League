package com.w1809809;

import java.util.*;

public class GUIConsoler {

    static Scanner INPUT = new Scanner(System.in);
    static Formula1ChampionshipManager formula1ChampionshipManager = new Formula1ChampionshipManager();



    public static void main(String[] args) {

        //Update_List_from_the_file
        formula1ChampionshipManager.retrieveTheDriverFromFile(Formula1ChampionshipManager.fileNameOfTheDriver);
        formula1ChampionshipManager.retrieveTheRaceFromFile(Formula1ChampionshipManager.fileNameOfTheRace);

        displayMenuLoop:

        while (true){
            System.out.println("\n***--- Welcome to Formula1 Championship League ---***");
            System.out.println("Press [1] to Create A New Formula1 Driver");
            System.out.println("Press [2] to Delete A Existing Formula1 Driver");
            System.out.println("Press [3] to Update the Formula1 Driver's Team");
            System.out.println("Press [4] to Get the Formula1Driver Statistics");
            System.out.println("Press [5] to Update the Race Status");
            System.out.println("Press [6] to Display All Drivers Statistics");
            System.out.println("Press [0] to Exit");

            System.out.print("\nSelect an option: ");
            int selectedOption;

            while (true){
                try {
                    selectedOption = INPUT.nextInt();
                } catch (InputMismatchException | IllegalArgumentException ex){
                    System.out.println("Invalid input, Try again!");
                    INPUT.next();
                    continue;
                }
                break;
            }

            switch (selectedOption){
                case 1:
                    createANewFormula1Driver();
                    break;

                case 2 :
                    deleteAExistingFormula1Driver();
                    break;
                case 3 :
                    updateTheFormula1DriverTeam();
                    break;
                case 4 :
                    getTheFormula1DriverStatistics();
                    break;
                case 5 :
                    updateTheRaceStatus();
                    break;
                case 6 :
                    displayAllDriversStatistics();
                    break;
                case 0 :
                    System.out.println("Thank you for using Formula1 Manager GUI Console! ");
                    break displayMenuLoop;

            }
        }

    }

    private static void displayAllDriversStatistics() {
        List<Formula1Driver> sortedListOfFormula1Drivers = formula1ChampionshipManager.sortDriversUsingPoints();

        String[][] sortedDriverListIn2d = new String[sortedListOfFormula1Drivers.size()+1][9];
        String[]  headersForTheTable= new String[]{"Driver Number", "Name", "Location", "Team", "1st Places", "2nd Places", "3rd Places", "Fastest Laps", "Hat-Tricks", "Total Points", "Participated Races"};
        sortedDriverListIn2d[0] = headersForTheTable;

        for (int j = 0; j < sortedListOfFormula1Drivers.size(); j++){
            Formula1Driver formula1Driver = sortedListOfFormula1Drivers.get(j);
            sortedDriverListIn2d[j+1] = new String[]{
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
        Map <Integer, Integer> columnLength = new HashMap<>();
        for (String[] strings : sortedDriverListIn2d){
            for (int i=0; i<strings.length; i++){
                if (columnLength.get(i) == null){
                    columnLength.put(i, strings[i].length());
                }
                if (columnLength.get(i) < strings[i].length()){
                    columnLength.put(i, strings[i].length());
                }
            }
        }

        final StringBuilder stringFormat = new StringBuilder("");
        columnLength.entrySet().stream().forEach(e -> stringFormat.append("| %" + "-" + e.getValue() + "s "));
        stringFormat.append("|\n");

        StringBuilder linesOfTheBoarder = new StringBuilder("");
        columnLength.forEach((key, value) -> {
            linesOfTheBoarder.append("*");
            for (int i=0; i<value+2; i++){
                linesOfTheBoarder.append("-");
            }
        });
        linesOfTheBoarder.append("*");

        System.out.println(linesOfTheBoarder);
        Arrays.stream(sortedDriverListIn2d).limit(1).forEach(item -> System.out.printf(stringFormat.toString(), item));
        System.out.println(linesOfTheBoarder);
        Arrays.stream(sortedDriverListIn2d).forEach(item -> {
            if (item != headersForTheTable){
                System.out.printf(stringFormat.toString(),item);
            }
        });
        System.out.println(linesOfTheBoarder);

    }

    private static void updateTheRaceStatus() {
        System.out.println("\nEnter the date (DD/MM/YYYY): ");
        String raceDate = INPUT.next();
        formula1ChampionshipManager.dateValidator(raceDate);

        if (formula1ChampionshipManager.dateValidator(raceDate)){
            String [] dateOfArray = raceDate.split("/");
            Date dateObject = new Date(Integer.parseInt(dateOfArray[0]), Integer.parseInt(dateOfArray[1]), Integer.parseInt(dateOfArray[2]));
            Map<Integer, Integer> resultOfTheRace = new HashMap<>();
            System.out.print("Enter Driver Number (with commas): ");
            String driverNumber = INPUT.next();

            String[] driverNumberList = driverNumber.split(",");

            for (String number : driverNumberList) {
                if (formula1ChampionshipManager.checkTheDriverExist(Integer.parseInt(number))){
                    System.out.print("Enter the place for " + number + " : ");
                    int points = INPUT.nextInt();
                    resultOfTheRace.put(Integer.parseInt(number), points);
                }else {
                    System.out.println("Invalid driver number! Enter the correct number.");
                }
            }

            formula1ChampionshipManager.updateTheRaceStatus(resultOfTheRace, dateObject);

        }else{
            System.out.println("Invalid Date! Input a correct date.");
        }

    }


    private static void getTheFormula1DriverStatistics() {
        System.out.print("\nEnter Driver Number: ");
        int existingDriverId = INPUT.nextInt();
        Formula1Driver driver = formula1ChampionshipManager.getTheFormula1DriverStatistics(existingDriverId);

        if (driver == null) {
            System.out.print("Driver Not Found!");
        } else {
            System.out.print("Driver Name: " + driver.getNameOfTheDriver());
            System.out.print("Driver Location: " + driver.getLocationOfTheDriver());
            System.out.print("Driver Team: " + driver.getTeamNameOfTheDriver());
            System.out.print("Number of Gold Medals: " + driver.getNumberOfGoldMedals());
            System.out.print("Number of Silver Medals: " + driver.getNumberOfSilverMedals());
            System.out.print("Number of Bronze Medals: " + driver.getNumberOfBronzeMedals());
            System.out.print("Enter Number of Fastest Laps: " + driver.getNumberOfFastestLaps());
            System.out.print("Enter Number of Hat-Tricks: " + driver.getNumberOfHatTricks());
            System.out.print("Number of total Points: " + driver.getTotalPoints());
            System.out.print("Number of Participated Races: " + driver.getNumberOfParticipation());
        }
    }

    private static void updateTheFormula1DriverTeam() {
        System.out.print("\nEnter Driver Number: ");
        int driverNumber = INPUT.nextInt();

        if(formula1ChampionshipManager.checkTheDriverExist(driverNumber)){
            System.out.println("Enter the New Team Name: ");
            String newTeamName = INPUT.next();
            formula1ChampionshipManager.updateTheFormula1DriverTeam(driverNumber, newTeamName);
        }else{
            System.out.println("Driver not Found " + driverNumber);
        }
    }

    private static void deleteAExistingFormula1Driver() {
        System.out.print("Enter Driver Number : ");
        int existingDriverId = INPUT.nextInt();
        formula1ChampionshipManager.deleteAExistingFormula1Driver(existingDriverId);
    }

    private static void createANewFormula1Driver() {
        System.out.print("Enter Driver Number: ");
        int driverNumber = INPUT.nextInt();

        System.out.print("Enter Driver Name : ");
        String nameOfTheDriver = INPUT.next();

        System.out.print("Enter Driver Location : ");
        String locationOfTheDriver = INPUT.next();

        System.out.print("Enter Team Name : ");
        String teamNameOfTheDriver = INPUT.next();

        System.out.print("Enter Number of Gold Medals: ");
        int numberOfGoldMedals = INPUT.nextInt();

        System.out.print("Enter Number of Silver Medals: ");
        int numberOfSilverMedals = INPUT.nextInt();

        System.out.print("Enter Number of Bronze Medals: ");
        int numberOfBronzeMedals = INPUT.nextInt();

        System.out.print("Enter Number of Participated Races: ");
        int numberOfParticipation = INPUT.nextInt();

        System.out.print("Enter Number of Fastest Laps: ");
        int numberOfFastestLaps = INPUT.nextInt();

        System.out.print("Enter the total Points: ");
        int totalPoints = INPUT.nextInt();

        System.out.print("Enter Number of Hat-Tricks: ");
        int numberOfHatTricks = INPUT.nextInt();

        Driver formula1NewDriver = new Formula1Driver(driverNumber, nameOfTheDriver, locationOfTheDriver, teamNameOfTheDriver, numberOfGoldMedals, numberOfSilverMedals, numberOfBronzeMedals, numberOfParticipation, numberOfFastestLaps, totalPoints, numberOfHatTricks);
        formula1ChampionshipManager.createANewFormula1Driver(formula1NewDriver);
    }

}
