package com.w1809809;

import java.util.List;
import java.util.Map;

public interface ChampionshipManager {
    void createANewFormula1Driver(Driver formula1Driver);
    void deleteAExistingFormula1Driver(int driverNumber);
    void updateTheFormula1DriverTeam(int driverNumber, String nameOfTheTeam);
    Formula1Driver getTheFormula1DriverStatistics(int driverNumber);
    void updateTheRaceStatus(Map<Integer, Integer> resultOfTheRace);
    void saveTheFormula1DriverToFile();
    void retrieveTheFormula1DriverFromFile();
    boolean checkTheDriverExist(int driverNumber);
    List <Formula1Driver> sortDriversUsingPoints();
}
