package com.w1809809;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class Formula1ChampionshipManager implements ChampionshipManager{

    static List<Formula1Driver> listOfTheFormula1driver = new ArrayList<>();
    static int[] points = {25,18,15,12,10,8,6,4,2,1};

    @Override
    public void createANewFormula1Driver(Driver formula1Driver) {
        System.out.println("\nCreate a New Formula1 Driver...");
        listOfTheFormula1driver.add((Formula1Driver) formula1Driver);
        saveTheFormula1DriverToFile();

    }

    @Override
    public void deleteAExistingFormula1Driver(int driverNumber) {
        System.out.println("\nDelete a Existing Formula1 Driver...\n");
        boolean isFound = false;

        // Find_The_Object_from_the_List
        List<Driver> checkForDriver = listOfTheFormula1driver.stream().filter(driver -> driver.getDriverNumber() == (driverNumber)).collect(Collectors.toList());

        for (Driver driver : checkForDriver) {
            if (driver.getDriverNumber() == driverNumber) {
                isFound = true;
                listOfTheFormula1driver.remove(driver);
            }
        }

        if (!isFound) {
            System.out.println("\nInvalid Driver Number");
        }
        saveTheFormula1DriverToFile();
    }

    @Override
    public void updateTheFormula1DriverTeam(int driverNumber, String newNameOfTheTeam) {
        System.out.println("\nUpdate the Formula1 Driver New Team...\n");

        List<Formula1Driver> checkForDriver = listOfTheFormula1driver.stream().filter(driver -> driver.getDriverNumber() == (driverNumber)).collect(Collectors.toList());

        for (Formula1Driver driver : checkForDriver) {
            if (driver.getDriverNumber() == driverNumber) {
                Formula1Driver updatedDriver = driver;
                updatedDriver.setTeamNameOfTheDriver(newNameOfTheTeam);
                listOfTheFormula1driver.set(listOfTheFormula1driver.indexOf(driver), updatedDriver);
            }
        }
        saveTheFormula1DriverToFile();
    }

    @Override
    public Formula1Driver getTheFormula1DriverStatistics(int driverNumber) {
        System.out.println("\nGet the Formula1 Driver Statistics...\n");

        Formula1Driver foundDriver = null;

        // Find_The_Object_from_the_List
        List<Driver> checkForDriver = listOfTheFormula1driver.stream().filter(driver -> driver.getDriverNumber() == (driverNumber)).collect(Collectors.toList());

        if (checkForDriver.size() == 0) {
            System.out.println("\nInvalid Driver Number...");
        } else {
            foundDriver = (Formula1Driver) checkForDriver.get(0); // TODO
        }

        return foundDriver;
    }

    @Override
    public void updateTheRaceStatus(Map<Integer, Integer> resultOfTheRace) {
        System.out.println("\nUpdate the Race Status...\n");

        resultOfTheRace.forEach((key, value) -> {
            Formula1Driver temporaryDriver = listOfTheFormula1driver.stream().filter(item -> item.getDriverNumber() == key).collect(Collectors.toList()).stream().findFirst().orElse(null);
            listOfTheFormula1driver.set(listOfTheFormula1driver.indexOf(temporaryDriver),updatePlaceAndPoints(temporaryDriver, value));
        });
        saveTheFormula1DriverToFile();
    }

    @Override
    public void saveTheFormula1DriverToFile() {
        System.out.println("Save the Formula1 Driver To the File...\n");
        try {
            FileOutputStream fileOutputStream = new FileOutputStream("DetailsOfTheDriver.txt", false);
            ObjectOutputStream objectOutputStream = new ObjectOutputStream(fileOutputStream);
            for (Formula1Driver driver : listOfTheFormula1driver) {
                objectOutputStream.writeObject(driver);
            }
            objectOutputStream.close();
            fileOutputStream.close();
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }

    @Override
    public void retrieveTheFormula1DriverFromFile() {
        System.out.println("\nRetrieve the Formula1 Driver From the File...\n");
        try {
            FileInputStream fileInputStream = new FileInputStream("DetailsOfTheDriver.txt");
            ObjectInputStream objectInputStream = new ObjectInputStream(fileInputStream);
            for (;;) {
                try {
                    Formula1Driver formula1Driver = (Formula1Driver) objectInputStream.readObject();
                    listOfTheFormula1driver.add(formula1Driver);
                }catch (EOFException ex) {
                    break;
                }
            }
            objectInputStream.close();
            fileInputStream.close();
        } catch (IOException | ClassNotFoundException ex) {
            ex.printStackTrace();
        }
    }

    @Override
    public boolean checkTheDriverExist(int driverNumber) {
        List<Driver> checkForDriver = listOfTheFormula1driver
                .stream()
                .filter(driver -> driver.getDriverNumber() == (driverNumber))
                .collect(Collectors.toList());
        return checkForDriver.size() != 0;
    }

    private Formula1Driver updatePlaceAndPoints(Formula1Driver temporaryDriver, Integer value) {
        if (value == 1) {
            temporaryDriver.setNumberOfGoldMedals(temporaryDriver.getNumberOfGoldMedals() + 1);
        }

        if (value == 2) {
            temporaryDriver.setNumberOfSilverMedals(temporaryDriver.getNumberOfSilverMedals() + 1);
        }

        if (value == 3) {
            temporaryDriver.setNumberOfBronzeMedals(temporaryDriver.getNumberOfBronzeMedals() + 1);
        }

        temporaryDriver.setTotalPoints(temporaryDriver.getTotalPoints() + points[value-1]);
        return temporaryDriver;
    }
}
