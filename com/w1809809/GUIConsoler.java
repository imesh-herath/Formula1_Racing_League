package com.w1809809;

import java.util.*;

public class GUIConsoler {

    static Scanner INPUT = new Scanner(System.in);
    static Formula1ChampionshipManager formula1ChampionshipManager = new Formula1ChampionshipManager();
    static int[] daysInALeapYear = {31,29,31,30,31,30,31,31,30,31,30,31};
    static int[] daysNotInALeapYear = {31,28,31,30,31,30,31,31,30,31,30,31};


    public static void main(String[] args) {

        //Update_List_from_the_file
        formula1ChampionshipManager.retrieveTheFormula1DriverFromFile();

        displayMenuLoop:

        while (true){
            System.out.println("\n***--- Welcome to Formula1 Championship League ---***");
            System.out.println("Press [1] to Create A New Formula1 Driver");
            System.out.println("Press [2] to Delete A Existing Formula1 Driver");
            System.out.println("Press [3] to Update the Formula1 Driver's Team");
            System.out.println("Press [4] to Get the Formula1Driver Statistics");
            System.out.println("Press [5] to Update the Race Status");
            System.out.println("Press [0] to Exit");

            System.out.print("Select an option: ");
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
                case 0 :
                    System.out.println("Thank you for using Formula1 Manager GUI Console! ");
                    break displayMenuLoop;

            }
        }

    }

    private static void updateTheRaceStatus() {
        System.out.println("\nEnter the date (YYYY/MM/DD): ");
        String raceDate = INPUT.next();
        dateValidator(raceDate);

        if (dateValidator(raceDate)){
            Map<Integer, Integer> ressultOfTheRace = new HashMap<>();
            System.out.print("Enter Driver Number (with commas): ");
            String driverNumber = INPUT.next();

            String[] driverNumberList = driverNumber.split(",");

            for (String number : driverNumberList) {
                if (formula1ChampionshipManager.checkTheDriverExist(Integer.parseInt(number))){
                    System.out.print("Enter the place for " + number + " : ");
                    int points = INPUT.nextInt();
                    ressultOfTheRace.put(Integer.parseInt(number), points);
                }else {
                    System.out.println("Invalid driver number! Enter the correct number.");
                }
            }

            formula1ChampionshipManager.updateTheRaceStatus(ressultOfTheRace);

        }else{
            System.out.println("Invalid Date! Input a correct date.");
        }

    }

    private static boolean dateValidator(String raceDate) {
        String[] dateFormatValidator = raceDate.split("/");

        int year = Integer.parseInt(dateFormatValidator[0]);
        int month = Integer.parseInt(dateFormatValidator[1]);
        int day = Integer.parseInt(dateFormatValidator[2]);

        if(month < 13 && day <= (year % 4 == 0 ? daysInALeapYear[month - 1] : daysNotInALeapYear[month - 1] )) {
            return true;
        }
     return false;
    }

    private static void getTheFormula1DriverStatistics() {
        System.out.print("\nEnter Driver Number: ");
        int existingDriverId = INPUT.nextInt();
        Formula1Driver driver = formula1ChampionshipManager.getTheFormula1DriverStatistics(existingDriverId);

        if (driver == null) {
            System.out.print("Driver Not Found!");
        } else {
            System.out.println("Driver Name: " + driver.getNameOfTheDriver());
            System.out.println("Driver Location: " + driver.getLocationOfTheDriver());
            System.out.println("Driver Team: " + driver.getTeamNameOfTheDriver());
            System.out.println("Number of Gold Medals: " + driver.getNumberOfGoldMedals());
            System.out.println("Number of Silver Medals: " + driver.getNumberOfSilverMedals());
            System.out.println("Number of Bronze Medals: " + driver.getNumberOfBronzeMedals());
            System.out.println("Number of total Points: " + driver.getTotalPoints());
            System.out.println("Number of Participated Races: " + driver.getNumberOfParticipation());
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

class ResultOfTheRace{
    private int driverNum;
    private int place;

    public ResultOfTheRace(int driverNum, int place){
        this.driverNum = driverNum;
        this.place = place;
    }

    public int getDriverNum() {
        return driverNum;
    }

    public int getPlace() {
        return place;
    }
}