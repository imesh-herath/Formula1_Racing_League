package com.w1809809;

import java.io.*;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.stream.Collectors;

public class Formula1ChampionshipManager implements ChampionshipManager {

    static List<Formula1Driver> listOfTheFormula1driver = new ArrayList<>();
    static List<Race> listOfRaces = new ArrayList<>();
    static int[] points = {25,18,15,12,10,8,6,4,2,1};
    static String fileNameOfTheDriver = "DetailsOfTheDriver.txt";
    static String fileNameOfTheRace = "DetailsOfTheRace.txt";


    @Override
    public void createANewFormula1Driver(Driver formula1Driver) {
        System.out.println("\nCreate a New Formula1 Driver...");
        listOfTheFormula1driver.add((Formula1Driver) formula1Driver);
        this.saveTheDriverToFile(fileNameOfTheDriver);

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
        this.saveTheDriverToFile(fileNameOfTheDriver);
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
        this.saveTheDriverToFile(fileNameOfTheDriver);
    }

    @Override
    public Formula1Driver getTheFormula1DriverStatistics(int driverNumber) {
        System.out.println("\nGet the Formula1 Driver Statistics...\n");

        //Find_the_object_from_the_list
        Formula1Driver checkTheDriver = listOfTheFormula1driver.stream().filter(driver -> driver.getDriverNumber() == (driverNumber)).collect(Collectors.toList()).stream().findFirst().orElse(null);

        if (checkTheDriver == null){
            System.out.println("Invalid Driver Number! Enter a correct one.");
        }

        return checkTheDriver;
    }

    @Override
    public void updateTheRaceStatus(Map<Integer, Integer> resultOfTheRace, Date date) {
        System.out.println("\nUpdate the Formula1 Race...\n");

        System.out.println("\nUpdate the Race Status...\n");
        Map<Formula1Driver, Integer> temporaryMap = new HashMap<>();

        resultOfTheRace.forEach((key, value) -> {
            Formula1Driver temporaryDriver = listOfTheFormula1driver.stream().filter(item -> item.getDriverNumber() == key).collect(Collectors.toList()).stream().findFirst().orElse(null);
            listOfTheFormula1driver.set(listOfTheFormula1driver.indexOf(temporaryDriver),updatePlaceAndPoints(temporaryDriver, value));
            temporaryMap.put(temporaryDriver, value);
        });
        Race tempRace = new Race(date, temporaryMap);
        listOfRaces.add(tempRace);

        this.saveTheDriverToFile(fileNameOfTheDriver);
        this.saveTheRaceToFile(fileNameOfTheRace);
    }

    @Override
    public void saveTheDriverToFile(String fileName) {
        System.out.println("Save the Formula1 Driver To the File...\n");
        try {
            FileOutputStream fileOutputStream = new FileOutputStream(fileName, false);
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
    public void saveTheRaceToFile(String fileName) {
        System.out.println("Save the Formula1 Race To the File...\n");
        try {
            FileOutputStream fileOutputStream = new FileOutputStream(fileName, false);
            ObjectOutputStream objectOutputStream = new ObjectOutputStream(fileOutputStream);
            for (Race race : listOfRaces) {
                objectOutputStream.writeObject(race);
            }
            objectOutputStream.close();
            fileOutputStream.close();
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }

    @Override
    public void retrieveTheDriverFromFile(String fileName) {
        System.out.println("\nRetrieve the Formula1 Driver From the File...\n");
        try {
            FileInputStream fileInputStream = new FileInputStream(fileName);
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
    public void retrieveTheRaceFromFile(String fileName) {
        try {
            FileInputStream fileInputStream = new FileInputStream(fileName);
            ObjectInputStream objectInputStream = new ObjectInputStream(fileInputStream);
            for (;;) {
                try {
                    Race race = (Race) objectInputStream.readObject();
                    listOfRaces.add(race);
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

    @Override
    public List<Formula1Driver> sortDriversUsingPoints() {
        Collections.sort(listOfTheFormula1driver, new ComparatorPoints());
        return listOfTheFormula1driver;
    }

    @Override
    public List<Formula1Driver> sortDriversUsing1stPlaces() {
        Collections.sort(listOfTheFormula1driver, new ComparatorWins());
        return listOfTheFormula1driver;
    }

    @Override
    public List<Race> filterByDriverNumber(String numberOfTheDriver) {
        List<Race> filteringRace = new ArrayList<>();

        for (Race race : listOfRaces){
            race.getDriverPlaceMap().forEach((key, value) -> {
                if (key.getDriverNumber() == Integer.parseInt(numberOfTheDriver)) {
                    filteringRace.add(race);
                }
            });
        }
        return filteringRace;
    }

    @Override
    public List<Race> sortByDate() {
        List<Race> listOfRacesCopy = listOfRaces;
        Collections.sort(listOfRacesCopy, (o1, o2) -> convertTheDate(o2.getDate()).compareTo(convertTheDate(o1.getDate())));
        return listOfRacesCopy;
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

    private java.util.Date convertTheDate(Date date) {
        DateFormat formatOfTheDate = new SimpleDateFormat("MM/dd/yyyy");
        java.util.Date dateObject = null;

        try {
            String temporaryStringDate = date.getMonth() + "/" + date.getDay() + "/" + date.getYear();
            dateObject = formatOfTheDate.parse(temporaryStringDate);
        } catch (ParseException ex) {
            ex.printStackTrace();
        }
        return dateObject;
    }
}

class ComparatorWins implements Comparator<Formula1Driver> {


    @Override
    public int compare(Formula1Driver o1, Formula1Driver o2) {
        return o2.getNumberOfGoldMedals() - o1.getNumberOfGoldMedals();
    }
}

class ComparatorPoints implements Comparator<Formula1Driver> {


    @Override
    public int compare(Formula1Driver o1, Formula1Driver o2) {
        return o2.getTotalPoints() - o1.getTotalPoints();
    }
}

