package com.w1809809;

import java.util.List;
import java.util.Map;

public interface ChampionshipManager {
    void createANewFormula1Driver(Driver formula1Driver);
    void deleteAExistingFormula1Driver(int driverNumber);
    void updateTheFormula1DriverTeam(int driverNumber, String nameOfTheTeam);
    Formula1Driver getTheFormula1DriverStatistics(int driverNumber);
    void updateTheRaceStatus(Map<Integer, Integer> resultOfTheRace, Date date);
    void saveTheDriverToFile(String fileName);
    void saveTheRaceToFile(String fileName);
    void retrieveTheDriverFromFile(String fileName);
    void retrieveTheRaceFromFile(String fileName);
    boolean checkTheDriverExist(int driverNumber);
    List <Formula1Driver> sortDriversUsingPoints();
    List <Formula1Driver> sortDriversUsing1stPlaces();

}
